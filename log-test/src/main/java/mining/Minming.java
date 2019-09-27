package mining;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinbiao.yao on 2019/9/10.
 */
public class Minming {
    public static void main(String[] args) {
        Minming minming = new Minming();
        List<String> tokens = new ArrayList<>();
        tokens.add("gfrOgSV5gG8qSHuVGj5ZDk1G5VTBIyF99t1DATOxpHCW7GZF7cB0ijhp66mdB72h");//main
        tokens.add("HknHUvXndJTTXmyXRCP203HoGOTytqwUnfg09VttKAGvzYB3STTCoeb34j8i0Cc6");
        tokens.add("jXB4PTw80UPCMArbKsoQNLd4UiPJLgcH43yNgYXoeQ0NBwbNCoux26BrgUpn49pt");//mfn

        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        scheduled.scheduleAtFixedRate(() -> {
            tokens.forEach(token -> {
                minming.getToken(token);
            });
        }, 0, 12, TimeUnit.MINUTES);

        Map<String, Long> map = new HashMap<>();
        if (map.size() == 0) {
            tokens.forEach(token -> {
                map.put(token, minming.getStatus(token));
            });
        }
        System.out.println(map.size());
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                map.forEach((k, v) -> {
                    if (v <= System.currentTimeMillis()) {
                        System.out.println("key:" + k + ";v:" + minming.formatTime(new Date(v)));
                        minming.receive(k);
                        minming.wakuang(k);
                        map.put(k, minming.getStatus(k));
                        System.out.println("key:" + k + ";new v:" + minming.formatTime(new Date(map.get(k))));
                    }
                });

            }
        }, 0, 10, TimeUnit.SECONDS);

        //周二开启
        /*scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                map.forEach((k, v) -> {
                    if(System.currentTimeMillis()>= 1569286800000L) {
                        minming.change(k);
                    }
                });
            }
        }, 0, 3, TimeUnit.MINUTES);*/

        //minming.getToken("0hoZecVrp7qgFaRokTE1CxcBNqU31ht0Ynbxj1pvGntnMDrLfrNulP8nx9q3nXR9");
        //minming.getStatus("0hoZecVrp7qgFaRokTE1CxcBNqU31ht0Ynbxj1pvGntnMDrLfrNulP8nx9q3nXR9");
        //minming.receive("0hoZecVrp7qgFaRokTE1CxcBNqU31ht0Ynbxj1pvGntnMDrLfrNulP8nx9q3nXR9");
        //minming.change("qPHLH78dQSuFXLYvgavVUlUXRA87vPqRYJaIGM7oI7AuxYhxpGm4E1riaeLINDYd");
    }

    public void change(String token) {
        String url = "https://www.fengjr.com/activity/api/v3/mining/invokeExchange/6C0C1643-7E18-4B5F-B72F-DBF857F556AF?productId=B4B8A6CD-D8F8-4DA9-BC03-0D6AC81F69C9";
        String result = HttpClientUtil.callRemote(url, null, "Authorization", token);
        System.out.println("***********兑换结果：" + result);
    }

    //刷新token
    public void getToken(String token) {
        String url = "https://www.fengjr.com/crm/api/v2/userlevelinfo/MYSELF?version=1.0";
        String result = HttpClientUtil.callRemoteByGet(url, "Authorization", token);
        System.out.println("刷新touken:" + JSONObject.parseObject(result).getString("message"));
    }

    //挖矿
    public void wakuang(String token) {
        String url = "http://www.fengjr.com/activity/api/v3/mining/7A33D6C8-922D-4ABA-8173-D7EDAFE8F8AB";
        String result = HttpClientUtil.callRemoteByGet(url, "Authorization", token);
        System.out.println("挖矿结果:" + result);
    }

    //领取金币
    public void receive(String token) {
        //获取金币列表
        String url = "http://www.fengjr.com/activity/api/v3/mining/getHomePageData/7A33D6C8-922D-4ABA-8173-D7EDAFE8F8AB";
        String result = HttpClientUtil.callRemoteByGet(url, "Authorization", token);

        JSONObject json = JSONObject.parseObject(result);
        JSONObject jsonObject = JSONObject.parseObject(json.getString("data"));
        List<JSONObject> goldCoinList = JSON.parseArray(jsonObject.getString("goldCoinList"), JSONObject.class);
        goldCoinList.forEach(goldCoin -> {
            System.out.println(goldCoin);
            String id = goldCoin.getString("id");
            String urlTmp = "https://www.fengjr.com/activity/api/v3/mining/getGoldCoin/MYSELF?couponId=" + id;
            String resultTmp = HttpClientUtil.callRemote(urlTmp, null, "Authorization", token);
            System.out.println("领取金币结果：" + JSONObject.parseObject(resultTmp).getString("success"));
        });
    }

    public Long getStatus(String token) {
        String url = "http://www.fengjr.com/activity/api/v3/mining/getHomePageData/7A33D6C8-922D-4ABA-8173-D7EDAFE8F8AB";
        String result = HttpClientUtil.callRemoteByGet(url, "Authorization", token);
        JSONObject json = JSONObject.parseObject(result);
        JSONObject jsonObject = JSONObject.parseObject(json.getString("data"));
        String status = jsonObject.getString("miningStatus");
        Long million = jsonObject.getLongValue("miningBeginTime");
        String surplusTime = formatTime(new Date(million + 18000000));
        System.out.println("获取状态:" + Thread.currentThread().getName() + ":" + status);
        System.out.println(token.substring(0, 5) + " 时间:" + surplusTime);
        if (!status.equals("IN_PROGRESS")) {
            wakuang(token);
        }
        return million + 18000000;
    }


    public String formatTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fmDate = simpleDateFormat.format(date);
        return fmDate;
    }


}
