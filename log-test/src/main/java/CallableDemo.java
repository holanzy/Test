import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinbiao.yao on 2019/6/17.
 */
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable*********");
        //计算3秒钟
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) {
        try {
            System.out.println("main*********");
            FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());
            //多个线程抢一个futureTask，计算结果只计算一次，要想计算多次，就要启动多个futureTask
            new Thread(futureTask, "AA").start();
            //两个线程但同一个futureTask，Callable*********只打印一次
            new Thread(futureTask, "BB").start();

            while (!futureTask.isDone()) {
                //等计算完退出循环
            }
            //获取Callable的计算结果，没计算完之前会阻塞在此
            int result = futureTask.get();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
