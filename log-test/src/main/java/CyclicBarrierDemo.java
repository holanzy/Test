import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/5/16.
 * <p>
 * CyclicBarrier做加法，加到指定数字后调用指定接口
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(8, new Runnable() {
            @Override
            public void run() {
                System.out.println("集齐7颗龙珠召唤神龙");
            }
        });

        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到第" + tempInt + "龙珠");
                try {
                    //没到7时会阻塞在这里
                    cyclicBarrier.await();
                    System.out.println("线程:"+Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        try {
            //TimeUnit.SECONDS.sleep(1);
            System.out.println("最后一个线程:"+Thread.currentThread().getName());
            cyclicBarrier.await();
            System.out.println("最后一个线程2:"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
