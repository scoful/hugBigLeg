package com.scoful.demo.otherBasicDemo.designPatternsDemo.Singleton;

/**
 * 单例模式--饿汉式
 *
 * @author scoful
 * @date 2020/5/21 3:34
 */
public class HungryDemo {
    private static HungryDemo instance = new HungryDemo();

    private HungryDemo() {
    }

    public static HungryDemo getInstance() {
        return instance;
    }
}
