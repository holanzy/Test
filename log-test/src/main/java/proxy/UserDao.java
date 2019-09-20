package proxy;

import proxy.DynamicProxy.IUserDao;

/**
 * Created by jinbiao.yao on 2019/3/21.
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }

    @Override
    public void update() {
        System.out.println("更新数据");
    }
}
