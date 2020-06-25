package object;

import org.openjdk.jol.info.ClassLayout;

public class NewObject {
    public static void main(String[] args) {
        Object o = new Object();

        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);

        synchronized (o) {
            s = ClassLayout.parseInstance(o).toPrintable();
            System.out.println(s);
        }
    }
}
