package trycatch;

/**
 * Created by jinbiao.yao on 2019/4/24.
 */
public class Singleton {
    public static int a = 0;
    private static Singleton singleton ;
    static {
        System.out.println("静态代码块");
        singleton = new Singleton();
    }

    private Singleton() {
        System.out.println("Singleton构造函数");
    }
    public static Singleton getSingleton() {
        a++;
        return singleton;
    }
}
