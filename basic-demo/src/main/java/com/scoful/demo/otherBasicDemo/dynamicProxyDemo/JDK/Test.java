package com.scoful.demo.otherBasicDemo.dynamicProxyDemo.JDK;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK版的动态代理
 *
 * @author scoful
 * @date 2020/5/26 14:53
 */
public class Test {
    public static void main(String[] args) {
        final UserDao userDao = new UserDaoImpl();
        //  newProxyInstance的三个参数解释：
        // 参数1：代理类的类加载器，同目标类的类加载器
        // 参数2：代理类要实现的接口列表，同目标类实现的接口列表
        // 参数3：回调，是一个InvocationHandler接口的实现对象，当调用代理对象的方法时，执行的是回调中的invoke方法
        // proxy为代理对象
        UserDao proxy = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),
                userDao.getClass().getInterfaces(), new InvocationHandler() {

                    @Override
                    // 参数proxy:被代理的对象
                    // 参数method:执行的方法，代理对象执行哪个方法，method就是哪个方法
                    // 参数args:执行方法的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("记录日志");
                        Object result = method.invoke(userDao, args);
                        return result;
                    }
                });
        //代理对象执行方法
        proxy.saveUser();
    }
}
