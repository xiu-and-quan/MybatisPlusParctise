package com.example.mptest.pojo.dto;

import lombok.*;

/**
 * @Author shizq18
 * @Date 2023/5/31
 * @Description
 */
@Getter
@Setter
@ToString
public class AbstractSubClassParamDTO extends Parent{

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
