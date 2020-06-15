package com.scoful.demo.otherBasicDemo.enumDemo;

/**
 * 枚举类例子
 *
 * @author scoful
 * @date 2020/5/22 12:06
 */
public enum Day {
    MONDAY("星期一", 1),
    TUESDAY("星期二", 2),
    WEDNESDAY("星期三", 3),
    THURSDAY("星期四", 4),
    FRIDAY("星期五", 5),
    SATURDAY("星期六", 6),
    SUNDAY("星期日", 7);

    private String desc;
    private int code;

    Day(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}

