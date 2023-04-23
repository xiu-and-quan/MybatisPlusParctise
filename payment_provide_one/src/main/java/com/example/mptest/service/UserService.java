package com.example.mptest.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mptest.entity.User;
import com.example.mptest.mapper.UserMapper;
import com.example.mptest.pojo.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    /*public void InsertUsers(){
        long start = System.currentTimeMillis();
        List<User> userList = new ArrayList<>();
        User user;
        for(int i = 1 ;i < 10; i++) {
            user = new User();
            user.setName("xiu"+i);
            userList.add(user);
        }
        userMapper.insertBatchSomeColumn(userList);
        long end = System.currentTimeMillis();
        System.out.println("一万条数据总耗时：" + (end-start) + "ms" );
    }*/

    public List<User> selectIn(List<Integer> ids){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(User::getId,ids);
        return userMapper.selectList(queryWrapper);
    }

    public List<User> selectInSet(Set<Integer> ids){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(User::getId,ids);
        return userMapper.selectList(queryWrapper);
    }

    public User findFirstElement(Integer id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().gt(User::getId,id).last("limit 1");
        return userMapper.selectOne(queryWrapper);
    }

    public List<UserVO> findPropertyByqueryWrapper(){
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            ids.add(i);
        }
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.lambda().select(User::getName).in(User::getId,ids);
        List<User> users = userMapper.selectList(queryWrapper);
        List<UserVO> list = new ArrayList<>();
        users.forEach(e -> {
            UserVO userVO = new UserVO();
                BeanUtils.copyProperties(userVO, e);
                list.add(userVO);
        });
        return list;
    }
}
