package threadLocal;

public class Main {

    int num = 0;
    ThreadLocal<Integer> threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        Main main = new Main();
        main.threadLocal.set(main.num);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        //main.num++;
                        System.out.println("Main:"+Thread.currentThread().getName());
                        main.threadLocal.set(main.threadLocal.get());

                    }
                }
            }, "线程" + i).start();

        }

        System.out.println(Thread.currentThread().getName()+","+main.threadLocal.get());
    }
}
