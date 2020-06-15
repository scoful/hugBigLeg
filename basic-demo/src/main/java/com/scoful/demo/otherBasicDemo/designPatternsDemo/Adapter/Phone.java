package com.scoful.demo.otherBasicDemo.designPatternsDemo.Adapter;

/**
 * 手机类
 *
 * @author scoful
 * @date 2020/5/21 16:25
 */
public class Phone {
    // 正常电压220v，是一个常量
    public static final int V = 220;
    private VoltageAdapter adapter;

    public void setAdapter(VoltageAdapter adapter) {
        this.adapter = adapter;
    }

    // 充电
    public void charge() {
        adapter.changeVoltage();
    }
}
