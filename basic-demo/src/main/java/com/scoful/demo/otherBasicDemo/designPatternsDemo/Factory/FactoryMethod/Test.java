package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.FactoryMethod;

/**
 * 工厂方法模式：
 * 有四个角色，抽象工厂模式，具体工厂模式，抽象产品模式，具体产品模式。
 * 不再是由一个工厂类去实例化具体的产品，而是由抽象工厂的子类去实例化产品
 *
 * @author scoful
 * @date 2020/5/21 17:29
 */
public class Test {
    public static void main(String[] args) {
        VehicleFactory broomFactory = new BroomFactory();
        MoveAble moveAble = broomFactory.create();
        moveAble.run();
    }
}
