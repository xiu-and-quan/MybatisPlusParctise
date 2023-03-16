package com.example.mptest.service;

import com.example.mptest.entity.User;
import com.example.mptest.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public void InsertUsers(){
        long start = System.currentTimeMillis();
        List<User> userList = new ArrayList<>();
        User user;
        for(int i = 1 ;i < 10; i++) {
            user = new User();
            user.setId(i);
            user.setName("name"+i);
            userList.add(user);
        }
        userMapper.insertBatchSomeColumn(userList);
        long end = System.currentTimeMillis();
        System.out.println("一万条数据总耗时：" + (end-start) + "ms" );
    }
}
