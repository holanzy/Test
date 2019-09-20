package JVMTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出，最大，最小堆内存20m，最大和最小值相等避免自动扩展
 * Created by jinbiao.yao on 2019/9/9.
 * OutOfMemoryError
 * <p>
 * VM Args:-Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        System.out.println("args = " + args);
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
