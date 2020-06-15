package com.scoful.demo.otherBasicDemo.designPatternsDemo.Singleton;

/**
 * 单例模式--线程不安全懒汉式
 *
 * @author scoful
 * @date 2020/5/21 3:41
 */
public class LazyUnsafeDemo {
    private static LazyUnsafeDemo singleton;

    private LazyUnsafeDemo() {
    }

    public static LazyUnsafeDemo getInstance() {
        if (singleton == null) {
            singleton = new LazyUnsafeDemo();
        }
        return singleton;
    }
}
