package com.example.mptest.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAndSchoolVO {

    Integer id;

    String name;

    String schoolName;
}
