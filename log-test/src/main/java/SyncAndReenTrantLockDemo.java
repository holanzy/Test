import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jinbiao.yao on 2019/5/17.
 * ReentrantLock精确唤醒
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程唤醒顺序
 * <p>
 * AA打印5次，BB打印10次,CC打印15次
 * 接着
 * AA打印5次，BB打印10次,CC打印15次
 * 。。。
 * 打印10轮
 */
class ShareResource {
    //A:1 B:2 C:3
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                //1号线程在此等待
                c1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            //通知2号线程
            number = 2;
            c2.signal();
            System.out.println("唤醒2号线程");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                //2号线程在此等待
                c2.await();
                System.out.println("2号线程醒了");
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            //通知3号线程
            number = 3;
            c3.signal();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //判断
            while (number != 3) {
                //3号线程在此等待
                c3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            //通知1号线程
            number = 1;
            c1.signal();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }
}

public class SyncAndReenTrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}
