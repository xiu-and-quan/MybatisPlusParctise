package com.example.mptest.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("personal_information")
public class PersonalInformation {

    @TableId(value = "id")
    /** 默认就是雪花算法 */
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "dateTime_creat", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime dateTimeCreated;

    @TableField(value = "dateTime_modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime dateTimeModified;

    @TableField(value = "user_created", fill = FieldFill.INSERT_UPDATE)
    private String userCreated;

    @TableField(value = "user_modified", fill = FieldFill.INSERT_UPDATE)
    private String userModified;
}
