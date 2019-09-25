package test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jinbiao.yao on 2019/8/30.
 */
public class Main2 {
    public static void main(String[] args) {
        List<Integer> nums = Lists.newArrayList(1, 2, 3);

        List<Integer> list = nums
                .stream()//转成Stream
                .filter(team -> team != 2)//过滤
                .collect(Collectors.toList());//流式处理对象函数

        System.out.println("count:"+Thread.activeCount());
        Thread.currentThread().getThreadGroup().list();

        String reslut = "{\"result\":{\"jdOrderId\":102544186275,\"orderPrice\":58,\"sku\":[{\"category\":6980,\"name\":\"58元礼品卡\",\"num\":1,\"price\":58,\"skuId\":1446040}]},\"resultCode\":\"0\",\"resultMessage\":\"成功\",\"success\":true}";
        String res = "{\"resultCode\":\"404\",\"resultMessage\":\"该订单不存在\",\"success\":false}";
        JSONObject jsonObject = JSONObject.parseObject(res);
        System.out.println(jsonObject.get("success"));

        Map<String, String> param = new HashMap<>();
        param.put("1", "a");
        param.put("2", "b");
        System.out.println(param.toString());
    }
}
