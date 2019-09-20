package thread;

/**
 * Created by jinbiao.yao on 2019/4/19.
 */
public class Thread1 implements Runnable {
    private Temp temp;

    public Thread1(Temp temp) {
        this.temp = temp;
    }

    public void runWait() {
        synchronized (this) {
            try {
                System.out.println("执行wait");
                temp.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runNotify() {
        synchronized (this) {
            System.out.println("执行wait");
            temp.notify();
        }
    }

    @Override
    public void run() {
        System.out.println("线程1开始");
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程1结束");
    }
}
