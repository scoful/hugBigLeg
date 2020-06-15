package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.FactoryMethod;

/**
 * @author scoful
 * @date 2020/5/21 17:40
 */
public class BroomFactory extends VehicleFactory {
    @Override
    MoveAble create() {
        return new Broom();
    }
}
