/**
 * Created by jinbiao.yao on 2019/5/22.
 * 父类子类初始顺序
 */
class Father{
    private  int i=test1();
    private static int j=method();

    static{
        System.out.print("1,");
    }
    Father() {
        System.out.print("2,");
    }
    {
        System.out.print("3,");
    }
    public int test1(){
        System.out.print("4,");
        return 1;
    }
    public static int method() {
        System.out.print("5,");
        return 1;
    }
}
public class InitTest extends Father {
    private  int i=test();
    private static int j=method();

    static{
        System.out.print("6,");
    }
    InitTest() {
        System.out.print("7,");
    }
    {
        System.out.print("8,");
    }

    public int test(){
        System.out.print("9,");
        return 1;
    }
    public static int method() {
        System.out.printf("10,");
        return 1;
    }
    public static void main(String[] args) {
        InitTest test = new InitTest();
    }
}
