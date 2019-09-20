package threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinbiao.yao on 2019/9/3.
 */
public class MyThreadPool {
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(9), new MyRejectedExecutionHanlder());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(()->{
                BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
                System.out.println(Thread.currentThread().getName() + "正在执行,队列：" + queue.size());
            }, "我的线程："+i);
            threadPoolExecutor.execute(t);
        }

        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
