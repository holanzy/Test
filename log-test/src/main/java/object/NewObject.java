package object;

import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

public class NewObject {
    public static void main(String[] args)  throws Exception{
        //偏向锁启动有延时，在jvm启动4秒后启动，参数可控制
        /**
         * 打开偏向锁效率不一定提升
         *
         */
        Thread.sleep(5000);

        Object o = new Object();

        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);

        synchronized (o) {

            s = ClassLayout.parseInstance(o).toPrintable();
            System.out.println(s);
        }


    }
}
