package com.scoful.demo.otherBasicDemo.designPatternsDemo.Adapter;

/**
 * 适配器模式
 * 将两种完全不同的事物联系到一起，就像现实生活中的变压器。
 *
 * @author scoful
 * @date 2020/5/21 16:31
 */
public class Test {
    public static void main(String[] args) {
        Phone phone = new Phone();
        VoltageAdapter voltageAdapter = new VoltageAdapter();
        phone.setAdapter(voltageAdapter);
        phone.charge();
    }
}
