package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.AbstractFactory;

/**
 * AK47
 *
 * @author scoful
 * @date 2020/5/21 17:55
 */
public class AK extends Weapon {
    @Override
    void shot() {
        System.out.println("shot");
    }
}
