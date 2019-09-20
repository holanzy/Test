package chain;

/**
 * Created by jinbiao.yao on 2019/1/17.
 */
public class HtmlFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        chain.index=2;
        //将字符串中出现的"<>"符号替换成"[]"
        request.requestStr = request.requestStr
                .replace('<', '[').replace('>', ']') +
                //后面添加的是便于我们观察代码执行步骤的字符串
                "----HTMLFilter()";
        chain.doFilter(request, response, chain);
        response.responseStr += "---HTMLFilter()";
    }
}
