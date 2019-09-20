package thread;

/**
 * Created by jinbiao.yao on 2019/4/19.
 */
public class Waiter implements Runnable {

    private Temp temp;

    public Waiter(Temp temp) {
        this.temp = temp;
    }

    @Override
    public void run() {
        try {
            temp.waiter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
