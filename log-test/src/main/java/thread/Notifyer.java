package thread;

/**
 * Created by jinbiao.yao on 2019/4/19.
 */
public class Notifyer implements Runnable {
    private Temp temp;

    public Notifyer(Temp temp) {
        this.temp = temp;
    }

    @Override
    public void run() {
        try {
            temp.notifyer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
