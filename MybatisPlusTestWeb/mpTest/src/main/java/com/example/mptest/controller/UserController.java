package com.example.mptest.controller;

import com.example.mptest.pojo.dto.UserDTO;
import com.example.mptest.pojo.vo.UserVO;
import com.example.mptest.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/testPutMappering")
    public UserVO testPutMappering(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getId());
        System.out.println(userDTO.getName());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO,userVO);
        return userVO;
    }
}
