package com.example.mptest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;
import static com.baomidou.mybatisplus.annotation.IdType.ID_WORKER;
@Data
@TableName("user")
@Builder
public class User {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "name")
    private String name;

}