package com.scoful.demo.otherBasicDemo.designPatternsDemo.Decorator;

/**
 * 面包类
 *
 * @author scoful
 * @date 2020/5/21 15:46
 */
public class Bread extends Food {

    private Food basicFood;

    public Bread(Food basicFood) {
        this.basicFood = basicFood;
    }

    @Override
    public String make() {
        return basicFood.make() + "+面包";
    }
}
