package com.test.task;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

/**
 * Created by jinbiao.yao on 2019/9/20.
 */
public class Main {
    public static void main(String[] args) {
       /* ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

            TBScheduleManagerFactory scheduleManagerFactory = new TBScheduleManagerFactory();

            try {
                Properties p = new Properties();
                p.put("zkConnectString", "192.168.3.117:2181");
                p.put("rootPath", "/home/platform/data");
                p.put("zkSessionTimeout", "60000");
                p.put("userName", "ScheduleAdmin");
                p.put("password", "password");
                p.put("isCheckParentPath", "true");
                scheduleManagerFactory.setApplicationContext(ctx);
                scheduleManagerFactory.init(p);
                scheduleManagerFactory.setZkConfig(convert(p));
            } catch (Exception e) {
                e.printStackTrace();
        }*/
    }
}
