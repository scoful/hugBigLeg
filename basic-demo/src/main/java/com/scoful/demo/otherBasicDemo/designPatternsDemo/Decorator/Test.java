package com.scoful.demo.otherBasicDemo.designPatternsDemo.Decorator;

/**
 * 装饰者模式
 * 对已有的业务逻辑进一步的封装，使其增加额外的功能，
 * 如Java中的IO流就使用了装饰者模式，用户在使用的时候，可以任意组装，达到自己想要的效果。
 *
 * @author scoful
 * @date 2020/5/21 15:54
 */
public class Test {
    public static void main(String[] args) {
        Food food = new Bread(new Vegetable(new Cream(new Food("香肠"))));
        System.out.println(food.make());
    }
}
