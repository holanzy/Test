import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by jinbiao.yao on 2019/5/16.
 * <p>
 * 信号灯,停车场思想,Semaphore(1) 只有一个线程可以做为锁使用
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //模拟3个车位
        Semaphore semaphore = new Semaphore(3);
        //6辆车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "开始抢车位******");
                    //Semaphore满时会阻塞在这里
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    //停车3秒
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "停车3秒后离开");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //释放车位,否则其他线程无法进入
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
