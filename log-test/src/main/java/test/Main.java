package test;

import org.apache.commons.lang3.time.DateUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
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
        BigDecimal passRate = new BigDecimal(1).divide(new BigDecimal(1079), 4, BigDecimal.ROUND_HALF_UP);
        System.out.println(passRate);

        try {
            String a = "123,";
            System.out.println(a.substring(0,a.lastIndexOf(",")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date date = DateUtils.addDays(new Date(), -10);
        System.out.println(new Date());

        System.out.println(date);
    }


}
