package com.scoful.demo.otherBasicDemo.enumDemo;

/**
 * @author scoful
 * @date 2020/5/22 11:12
 */
public class Test {


    public static void main(String[] args) {
        sayHalo(Day.MONDAY.getDesc());
        System.out.println(Day.MONDAY);

    }

    private static void sayHalo(String desc) {
        System.out.println(desc);
    }
}
