package test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinbiao.yao on 2019/7/12.
 */
public class Main {

    static final int hash(String key) {
        int h;
        //System.out.println(h = key.hashCode());
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("123", "a");
        map.put("456","b");
        System.out.println(hash("123"));
    }


}