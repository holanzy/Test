import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * Created 2019/5/15.
 * CountDownLatch倒计时类,从初始值减到0才继续向下执行
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "上完自习，离开教室");
                //执行一次减一次
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            //countDownLatch减到0才继续往下执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "***********班长最后离开教室关灯走人");
    }
}
