package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.AbstractFactory;


/**
 * @author scoful
 * @date 2020/5/21 17:48
 */
public abstract class AbstractFactory {
    public abstract Vehicle createVehicle();

    public abstract Weapon createWeapon();

    public abstract Desk createDesk();
}
