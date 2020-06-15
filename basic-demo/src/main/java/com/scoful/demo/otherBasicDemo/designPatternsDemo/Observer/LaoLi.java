package com.scoful.demo.otherBasicDemo.designPatternsDemo.Observer;

/**
 * @author scoful
 * @date 2020/5/21 15:18
 */
public class LaoLi implements Person {
    private String name = "小李";

    public LaoLi() {
    }

    @Override
    public void getMessage(String s) {
        System.out.println(name + "接到了小美打过来的电话，电话内容是：" + s);
    }
}
