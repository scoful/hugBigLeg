package com.scoful.demo.otherBasicDemo.designPatternsDemo.Singleton;

/**
 * 单例模式--静态内部类式
 *
 * @author scoful
 * @date 2020/5/21 3:37
 */
public class InnerStaticDemo {
    private InnerStaticDemo() {
    }

    public static final InnerStaticDemo getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final InnerStaticDemo INSTANCE = new InnerStaticDemo();
    }
}
