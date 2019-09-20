package threadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jinbiao.yao on 2019/9/3.
 * 自定义拒绝策略
 */
public class MyRejectedExecutionHanlder implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("当前被拒绝任务为：" + r.toString()+"executor:"+executor.isShutdown());
    }
}
