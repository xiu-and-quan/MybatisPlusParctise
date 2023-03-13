package com.example.mptest.pojo.vo;

import com.example.mptest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    public Integer UserId;

    public String userName;
}
