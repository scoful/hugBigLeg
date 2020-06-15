package com.scoful.demo.otherBasicDemo.designPatternsDemo.Adapter;

/**
 * 变压器
 *
 * @author scoful
 * @date 2020/5/21 16:25
 */
public class VoltageAdapter {
    // 改变电压的功能
    public void changeVoltage() {
        System.out.println("正在充电...");
        System.out.println("原始电压：" + Phone.V + "V");
        System.out.println("经过变压器转换之后的电压：" + (Phone.V - 200) + "V");
    }
}
