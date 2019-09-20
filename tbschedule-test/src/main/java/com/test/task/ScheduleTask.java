package com.test.task;

import com.taobao.pamirs.schedule.AbstractOnceScheduleTask;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jinbiao.yao on 2019/9/20.
 */
@Service
public class ScheduleTask extends AbstractOnceScheduleTask {

    @Override
    public void runOnce(String s, String s1, int i, List list, int i1) {
        System.out.println("sadf");
    }
}
