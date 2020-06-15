package com.scoful.demo.otherBasicDemo.designPatternsDemo.Proxy;

/**
 * @author scoful
 * @date 2020/5/21 18:04
 */
public class NormalHome implements ProxyInterface {

    @Override
    public void marry() {
        System.out.println("我们结婚啦～");
    }
}
