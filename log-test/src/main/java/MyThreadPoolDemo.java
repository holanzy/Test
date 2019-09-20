import java.util.concurrent.*;

/**
 * Created on 2019/6/19.
 * 手写线程池
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= 15; i++) {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "  办理业务");
            });
        }


    }

}
