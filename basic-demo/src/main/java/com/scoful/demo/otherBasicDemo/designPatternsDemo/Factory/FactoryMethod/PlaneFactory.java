package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.FactoryMethod;

/**
 * @author scoful
 * @date 2020/5/21 17:39
 */
public class PlaneFactory extends VehicleFactory {
    @Override
    MoveAble create() {
        return new Plane();
    }
}
