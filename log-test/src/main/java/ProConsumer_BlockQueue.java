import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jinbiao.yao on 2019/5/17.
 * 生产者消费者模式
 * 阻塞队列实现
 */
class MyResources {
    //默认开启，进行生产+消费,此处volatile用于线程可见性
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResources(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String date = null;
        boolean retValue;
        while (FLAG) {
            //相当于++i
            date = atomicInteger.incrementAndGet() + "";
            //队列插入数据,阻塞时间
            retValue = blockingQueue.offer(date, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "插入队列" + date + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "插入队列" + date + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(Thread.currentThread().getName() + "生产叫停，表示FLAG=false,生产停止");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            //取队列数据
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "超过2秒钟没有取到蛋糕，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费队列蛋糕" + result + "成功");
        }
    }

    public void stop() throws Exception {
        this.FLAG = false;
    }
}

public class ProConsumer_BlockQueue {

    public static void main(String[] args) {
        MyResources myResources = new MyResources(new ArrayBlockingQueue<String>(3));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "生产线程启动");
            try {
                myResources.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "生产线程启动");
            try {
                myResources.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);

            System.out.println();
            System.out.println();
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            myResources.stop();
            System.out.println("5秒钟时间到，main线程停止，活动结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
