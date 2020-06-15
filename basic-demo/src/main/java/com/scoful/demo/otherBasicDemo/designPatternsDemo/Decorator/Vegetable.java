package com.scoful.demo.otherBasicDemo.designPatternsDemo.Decorator;

/**
 * 蔬菜类
 *
 * @author scoful
 * @date 2020/5/21 15:52
 */
public class Vegetable extends Food {
    private Food basicFood;

    public Vegetable(Food basicFood) {
        this.basicFood = basicFood;
    }


    @Override
    public String make() {
        return basicFood.make() + "+蔬菜";
    }
}
