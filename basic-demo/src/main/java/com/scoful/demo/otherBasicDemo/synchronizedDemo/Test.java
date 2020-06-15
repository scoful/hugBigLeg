package com.scoful.demo.otherBasicDemo.synchronizedDemo;

/**
 * @author scoful
 * @date 2020/6/4 18:25
 */
public class Test {


    public static void main(String[] args) {

        testSynchronized();
    }

    public static void testSynchronized() {
        Integer a = 0;
        synchronized (a) {
            System.out.println("halo");
        }
    }

}
