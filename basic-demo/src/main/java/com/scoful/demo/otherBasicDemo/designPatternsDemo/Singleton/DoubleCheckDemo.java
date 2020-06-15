package com.scoful.demo.otherBasicDemo.designPatternsDemo.Singleton;

/**
 * 单例模式--双重校验锁式
 *
 * @author scoful
 * @date 2020/5/21 3:43
 */
public class DoubleCheckDemo {
    private volatile static DoubleCheckDemo singleton;

    private DoubleCheckDemo() {
    }

    public static DoubleCheckDemo getSingleton() {
        if (singleton == null) {
            synchronized (DoubleCheckDemo.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckDemo();
                }
            }
        }
        return singleton;
    }
}
