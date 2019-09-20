import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/5/16.
 * 阻塞队列
 * ArrayBolckingQueue:是一个基于数组结构的有界阻塞队列，元素按FIFO排序
 * LinkedBolckingQueue:是一个基于链表的阻塞队列，元素按FIFO排序，吞吐量高于ArrayBolckingQueue
 * SynchronousQueue:一个不存储元素的阻塞队列，每个插入操作必须等另一个线程调用移除操作才行，否则就一直阻塞，吞吐量高
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

        BlockingQueue blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                //线程BBB消费一个，AAA放一个
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingQueue.put("1");

                //1被消费之前会阻塞在这里
                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
