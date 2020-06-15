package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.SimpleFactory;

/**
 * 简单工厂模式：
 * 一个抽象的接口，多个抽象接口的实现类，一个工厂类，用来实例化抽象的接口
 *
 * @author scoful
 * @date 2020/5/21 17:24
 */
public class Test {
    public static void main(String[] args) {

        Car c = Factory.getCarInstance("Benz");
        if (c != null) {
            c.run();
            c.stop();
        } else {
            System.out.println("造不了这种汽车");
        }

    }
}
