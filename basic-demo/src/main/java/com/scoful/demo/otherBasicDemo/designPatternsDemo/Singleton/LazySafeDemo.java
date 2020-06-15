package com.scoful.demo.otherBasicDemo.designPatternsDemo.Singleton;

/**
 * 单例模式--加锁线程安全懒汉式
 *
 * @author scoful
 * @date 2020/5/19 20:14
 */
public class LazySafeDemo {

    private static LazySafeDemo instance;

    private LazySafeDemo() {
    }

    public static synchronized LazySafeDemo getInstance() {
        if (instance == null) {
            instance = new LazySafeDemo();
        }
        return instance;
    }


}

