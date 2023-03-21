package com.example.mptest.enums;

public enum ChecklistStatusEnums {

    UNFINISHED("0", "未完成"),
    PROCESSING("1", "进行中"),
    FINISHED("2", "已完成"),
    FAILED("3", "失败");

    private String code;
    private String value;

    ChecklistStatusEnums(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String code() {
        return code;
    }

    public String value() {
        return value;
    }
}
