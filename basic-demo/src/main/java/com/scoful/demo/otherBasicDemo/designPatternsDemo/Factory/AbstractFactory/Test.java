package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.AbstractFactory;

/**
 * 抽象工厂模式：
 * 与工厂方法模式不同的是，工厂方法模式中的工厂只生产单一的产品，而抽象工厂模式中的工厂生产多个产品
 *
 * @author scoful
 * @date 2020/5/21 17:47
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory defaultFactory = new DefaultFactory();
        Vehicle vehicle = defaultFactory.createVehicle();
        vehicle.run();
        Desk desk = defaultFactory.createDesk();
        desk.height();
        Weapon weapon = defaultFactory.createWeapon();
        weapon.shot();

    }
}
