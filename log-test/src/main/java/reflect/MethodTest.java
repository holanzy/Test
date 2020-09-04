package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Method.invoke使用方法
 */
public class MethodTest {
    private String name;
    private int age;

    MethodTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void print() {
        System.out.println("类：ethodTest，方法：print,name:" + name + " age:" + age);
    }

    public static void main(String[] args) {
        try {

            MethodTest methodTestInstance = new MethodTest("Tom", 25);
            Method method = MethodTest.class.getMethod("print");
            method.invoke(methodTestInstance);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
