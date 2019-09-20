package thread;

/**
 * Created by jinbiao.yao on 2019/4/19.
 */
public class Temp {
    int count = 0;

    public void waiter() throws InterruptedException {
        synchronized (this) {
            System.out.println("等待");
            wait();
            System.out.println(this.count);
        }
    }

    public void notifyer() throws InterruptedException {
        synchronized (this) {
            try {
                Thread.sleep(1000);
                System.out.println("唤醒");
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread() + "notifyer:" + i);
                    count += i;
                }
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
