package com.example.mptest.pojo.dto;

/**
 * @Author shizq18
 * @Date 2023/5/31
 * @Description
 */
public class TestDTO {

    public int id;

    public String name;

    public String state;

    public TestDTO(int id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
    public TestDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
