/**
 * Created by jinbiao.yao on 2019/8/21.
 * 模拟CAS
 */
public class CompareAndSet {

    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int expectValue = cas.get();
                    boolean b = cas.compareAndSet(expectValue, j);
                    System.out.println(" "+Thread.currentThread().getName() + " " + b + " " + expectValue + "," + j);
                }
            }, "th-" + j).start();
        }
    }
}


class CompareAndSwap {

    private int value;

    //获取内存值
    public synchronized int get() {
        return value;
    }

    //比较
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        System.out.print("name:" + Thread.currentThread().getName() + " " + oldValue + " ");
        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    //设置
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}