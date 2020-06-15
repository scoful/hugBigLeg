package com.scoful.demo.otherBasicDemo.designPatternsDemo.Decorator;

/**
 * @author scoful
 * @date 2020/5/21 15:44
 */
public class Food {
    private String foodName;

    public Food() {
    }

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String make() {
        return foodName;
    }

}
