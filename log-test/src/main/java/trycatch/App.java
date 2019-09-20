package trycatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinbiao.yao on 2019/4/23.
 */
public class App {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getSingleton();
        System.out.println("a:"+Singleton.a);
        Singleton singleton2 = Singleton.getSingleton();
        System.out.println("b:"+Singleton.a);
    }

    public static void test() {


    }


}
