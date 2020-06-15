package com.scoful.demo.otherBasicDemo.designPatternsDemo.Decorator;

/**
 * 奶油类
 *
 * @author scoful
 * @date 2020/5/21 15:51
 */
public class Cream extends Food {

    private Food basicFood;

    public Cream(Food basicFood) {
        this.basicFood = basicFood;
    }

    @Override
    public String make() {
        return basicFood.make() + "+奶油";
    }
}
