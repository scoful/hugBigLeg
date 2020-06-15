package com.scoful.demo.otherBasicDemo.dynamicProxyDemo.JDK;

/**
 * @author scoful
 * @date 2020/5/26 13:27
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void saveUser() {
        System.out.println("持久层：用户保存功能");
    }
}
