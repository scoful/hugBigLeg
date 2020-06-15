package com.scoful.demo.otherBasicDemo.designPatternsDemo.Factory.SimpleFactory;

/**
 * @author scoful
 * @date 2020/5/21 17:22
 */
public class Factory {

    public static Car getCarInstance(String type) {
        Car c = null;
        if ("Benz".equals(type)) {
            c = new Benz();
        }
        if ("Ford".equals(type)) {
            c = new Ford();
        }
        return c;
    }


}
