package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.AbstractFactory;

/**
 * @author scoful
 * @date 2020/5/21 17:51
 */
public class DefaultFactory extends AbstractFactory {
    @Override
    public Vehicle createVehicle() {
        return new Ferrari();
    }

    @Override
    public Weapon createWeapon() {
        return new AK();
    }

    @Override
    public Desk createDesk() {
        return new Round();
    }
}
