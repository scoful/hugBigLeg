package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.SimpleFactory;

/**
 * @author scoful
 * @date 2020/5/21 17:19
 */
public class Benz implements Car {
    @Override
    public void run() {
        System.out.println("Benz开始启动了。。。");
    }

    @Override
    public void stop() {
        System.out.println("Benz停车了。。。");
    }
}
