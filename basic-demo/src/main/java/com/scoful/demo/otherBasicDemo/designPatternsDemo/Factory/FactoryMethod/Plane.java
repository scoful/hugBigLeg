package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.FactoryMethod;

/**
 * 具体产品角色
 *
 * @author scoful
 * @date 2020/5/21 17:34
 */
public class Plane implements MoveAble {
    @Override
    public void run() {
        System.out.println("plane....");
    }
}
