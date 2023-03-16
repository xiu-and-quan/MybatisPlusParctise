package com.example.mptest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName
public class User {

    @TableId(value = "id")
    private Integer id;
    @TableField
    private String name;

    public User(String name) {
        this.name = name;
    }
}