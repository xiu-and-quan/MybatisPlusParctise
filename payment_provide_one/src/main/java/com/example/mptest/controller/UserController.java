package com.example.mptest.controller;

import com.example.mptest.entity.User;
import com.example.mptest.mapper.UserMapper;
import com.example.mptest.pojo.dto.OpenFeignDTO;
import com.example.mptest.pojo.dto.UserDTO;
import com.example.mptest.pojo.vo.UserVO;
import com.example.mptest.service.UserService;
import com.example.mptest.until.BeanObjectCopyUtils;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PutMapping("/testPutMappering")
    public UserVO testPutMappering(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getId());
        System.out.println(userDTO.getName());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO,userVO);
        return userVO;
    }

    /**
     * get请求 一个参数 简单类型
     */
    @GetMapping("/openfeign/findById")
    public String testOpenfeign(@RequestParam Integer id){
        return userMapper.selectById(id).getName();
    }

    /**
     * post请求 带参数的openfeign调用
     */
    @PostMapping ("/openfeign/findByBodyAndParam")
    List<String> findByBodyAndParam(@RequestParam Integer id,
                                    @RequestBody OpenFeignDTO openFeignDTO){
        List<String> list = new ArrayList<>();
        String name = openFeignDTO.getName();
        System.out.println(name);
        list.add(name);
        return list;
    }

    @PostMapping("/insert")
    void insert(@RequestBody User user){
        //User user = BeanObjectCopyUtils.copyObject(new User(),userDTO);
        userMapper.inserBySnowFlower(user);
    }
}
