package proxy.Cglib;

import proxy.UserDao;

/**
 * Created by jinbiao.yao on 2019/3/21.
 * cglibe动态代理
 */
public class App {
    public static void main(String[] args) {
//目标对象
        UserDao target = new UserDao();
        //代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }


}
