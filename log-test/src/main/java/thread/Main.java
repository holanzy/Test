package thread;

/**
 * Created by jinbiao.yao on 2019/4/19.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Temp temp = new Temp();
            Waiter waiter = new Waiter(temp);
            Notifyer notifyer = new Notifyer(temp);

            Thread thread1 = new Thread(waiter);
            Thread thread2 = new Thread(notifyer);
            thread1.start();
            thread2.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}