import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by jinbiao.yao on 2019/4/16.
 * 手写自旋锁
 */
class MyData {
    volatile int a = 1;

    public void setA() {
        this.a++;
    }
}

public class CAS {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(Thread.currentThread().getName() + "\t 加锁");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock()");
    }

    public static void main(String[] args) {
        CAS test = new CAS();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.myLock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.myUnLock();
            }
        }, "线程1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.myLock();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.myUnLock();
            }
        }, "线程2").start();
    }
}
