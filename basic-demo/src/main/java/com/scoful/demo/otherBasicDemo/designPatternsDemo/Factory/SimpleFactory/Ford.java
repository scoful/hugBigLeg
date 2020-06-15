package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.SimpleFactory;

/**
 * @author scoful
 * @date 2020/5/21 17:21
 */
public class Ford implements Car {
    @Override
    public void run() {
        System.out.println("Ford开始启动了。。");
    }

    @Override
    public void stop() {
        System.out.println("Ford停车了。。");
    }
}
