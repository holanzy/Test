package reflect;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * 反射
 * Created by jinbiao.yao on 2019/3/25.
 */
public class App {
    public static void main(String[] args) {
        try {
            Person person = new Person("luoxn28", 23);
            Class clazz = person.getClass();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
                Method method = descriptor.getReadMethod();
                Object value = method.invoke(person);

                System.out.println(key + ":" + value);

            }

            //不会初始化静态变量
            Class clazz1 = Person.class;
            Field[] fields1 = clazz.getDeclaredFields();
            for (Field field : fields1) {
                String key = field.getName();
                PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz1);
                Method method = descriptor.getReadMethod();
                Object value = method.invoke(person);

                System.out.println(key + ":" + value);

            }

            // 会初始化
            //Class clazz2 = Class.forName("zzz.Base");
            SerializeCustomer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void SerializeCustomer() throws FileNotFoundException,
            IOException {
        Person customer = new Person("gacl", 25);
        // ObjectOutputStream 对象输出流
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("D:/Customer.txt")));
        oo.writeObject(customer);
        System.out.println("Customer对象序列化成功！");
        oo.close();
    }
}
