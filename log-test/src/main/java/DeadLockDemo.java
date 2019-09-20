import java.util.concurrent.TimeUnit;

/**
 * Created on 2019/6/19.
 * 死锁
 * 死锁定位：
 * jps -l查看进程号
 * jstack 进程号 查看详细信息
 */
class HoleLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoleLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " 自己持有:" + lockA + " 尝试获得:" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " 自己持有:" + lockB + " 尝试获得:" + lockA);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoleLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoleLockThread(lockB, lockA), "ThreadBBB").start();
    }
}
