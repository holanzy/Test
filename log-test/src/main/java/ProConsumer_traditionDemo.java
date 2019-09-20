import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jinbiao.yao on 2019/5/17.
 * 题目：一个初始值为0的变量，一个线程加1，一个线程减1,5轮
 * 传统多线程,生产者和消费者模式
 * <p>
 * 线程 操作(方法调用) 资源类
 * 判断 干活 通知（唤醒其他线程）
 * 防止虚假唤醒机制
 * <p>
 * <p>
 * 这段程序不会产生虚假唤醒，因为只有两个线程，当2个（或多个）线程处于await时，另一个线程调用notifyall,
 * 一个线程唤醒抢到锁并执行完成，释放锁，另一个线程因为速度慢，刚完成唤醒，会再去抢锁，它认为是和另一个线程在抢锁，抢到锁后执行程序，造成重复执行，虚假唤醒
 * 虚假唤醒：
 * 假设此时有线程A,C买票，线程A调用wait方法进入等待队列，线程C买票时发现线程B在退票，获取锁失败，
 * 线程C阻塞，进入阻塞队列，线程B退票时，余票数量+1（满足条件2 等待条件发生），线程B调用notify方法后，
 * 线程C马上竞争获取到锁，购票成功后余票为0，而线程A此时正处于wait方法醒来过程中的第三步（竞争获取锁获取锁），
 * 当线程C释放锁，线程A获取锁后，会执行购买的操作，而此时是没有余票的。
 */

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            //System.out.println(number);
            //判断,这里用if的话会导致虚假唤醒
            while (number != 0) {
                //线程阻塞，等待，不能生产,会释放锁
                condition.await();
                //while循环，被唤醒后再判断一次while条件
            }

            /*if (number != 0) {
                //线程阻塞，等待，不能生产,会释放锁
                condition.await();
                被唤醒后有可能别的线程抢先执行完了，使number！=0了
            }*/
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + " " + number);
            //唤醒线程
            condition.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            //判断
            while (number == 0) {
                //等待，不能生产
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + " " + number);
            //唤醒线程
            condition.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }
}

public class ProConsumer_traditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        try {
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    //System.out.println("i=" + i);
                    shareData.increment();
                }
            }, "AAA").start();
            TimeUnit.SECONDS.sleep(3);
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    shareData.decrement();
                }
            }, "bbb").start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
