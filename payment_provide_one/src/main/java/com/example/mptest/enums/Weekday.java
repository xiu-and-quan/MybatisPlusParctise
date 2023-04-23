package com.example.mptest.enums;

/**
 * 星期枚举类
 * 括号里面的是value，类似map，
 */
public enum Weekday {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    //这个是属性值
    private int value;

    Weekday(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
