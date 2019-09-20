package mining;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class HttpClientUtil {
    /**
     * @param url
     * @param keyValueMap
     * @param charSet
     * @return
     */
    public static String executeHttpRequest(String url, Map<String, String> keyValueMap, String charSet) {
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        try {
            //设置请求参数
            if (keyValueMap != null) {
                Iterator it = keyValueMap.entrySet().iterator();
                NameValuePair[] parameters = new NameValuePair[keyValueMap.size()];
                int c = 0;
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
                    NameValuePair nvp = new NameValuePair();
                    nvp.setName(entry.getKey());
                    nvp.setValue(entry.getValue());
                    parameters[c] = nvp;
                    c++;
                }
                postMethod.addParameters(parameters);
            }
            log.debug("query uri ===============" + postMethod.getURI());
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
            postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 100000);
            int statusCode = client.executeMethod(postMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.info("request '" + url + "' failed,the status is not 200,status:" + statusCode);
                return null;
            }
            String responseBody = postMethod.getResponseBodyAsString();
            return responseBody;
        } catch (Exception e) {
            log.error("executeHttpRequest发生异常！请检查网络和参数,url:" + url, e);
        } finally {
            postMethod.releaseConnection();
        }
        return null;

    }


    private static PostMethod getPost(String url, String data) throws UnsupportedEncodingException {
        PostMethod method = new PostMethod(url);
        //method.setRequestEntity(new StringRequestEntity(data, "application/json", "utf-8"));
        return method;
    }


    public static String callRemote(String url, String jsonData, String authorization, String value) {

        HttpClient client = new HttpClient();

        log.info("请求参数:" + jsonData);
        PostMethod method = null;
        String result = null;
        try {

            method = getPost(url, jsonData);
            if (authorization != null) {
                method.addRequestHeader(authorization, value);
            }
            client.executeMethod(method);


            byte[] rsbyte = method.getResponseBody();
            if (rsbyte != null) {

                result = new String(rsbyte, "UTF-8");
                log.info("远程返回字符串::" + result);
            }


        } catch (Exception e) {
            log.error("远程请求异常url::" + url, e);
        }
        return result;
    }

    public static String callRemoteByGet(String url, String authorization, String value) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        HttpClient httpclient = new HttpClient();

        GetMethod method = new GetMethod(url);

        if (authorization != null) {
            method.addRequestHeader(authorization, value);
        }

        String result = null;
        int statusCode = 200;

        try {
            statusCode = httpclient.executeMethod(method);

            if (HttpStatus.SC_OK == statusCode) {
                byte[] rsbyte = method.getResponseBody();
                /*Header[] headers = method.getRequestHeaders();
                System.out.println(headers);
                Header header = method.getRequestHeader("Authorization");*/
                if (rsbyte != null) {
                    result = new String(rsbyte, "UTF-8");
                    log.info("调用成功，url: " + url + "，远程返回字符串: " + result);
                }
            } else {
                log.info("调用异常，url: " + url + "，返回码：" + statusCode);
            }

        } catch (Exception e) {
            log.error("Http Client invoke error, method:getObjByURL, statusCode:" + statusCode, e);
        }

        return result;
    }

}
