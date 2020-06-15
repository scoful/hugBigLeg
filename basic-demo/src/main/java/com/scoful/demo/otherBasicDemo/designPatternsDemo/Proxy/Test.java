package com.scoful.demo.otherBasicDemo.designPatternsDemo.Proxy;

/**
 * 代理模式
 *
 * @author scoful
 * @date 2020/5/21 18:05
 */
public class Test {
    public static void main(String[] args) {
        WeddingCompany weddingCompany = new WeddingCompany(new NormalHome());
        weddingCompany.marry();
    }
}
