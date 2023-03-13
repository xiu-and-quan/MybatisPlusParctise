package com.example.mptest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mptest.entity.User;
import com.example.mptest.mapper.UserMapper;
import com.example.mptest.pojo.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpTestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testupdate(){
        User u1 = userMapper.selectById(3);
        u1.setName("snow");
        userMapper.updateById(u1);
    }

    //批量插入
    @Test
    public void testBatchInsert(){
        User u1 = new User("xiu");
        User u2 = new User("dongMa");
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        userMapper.batchSave(users);
    }

    //批量删除
    @Test
    public void testBatchDelete(){
        List<Integer> users = new ArrayList<>();
        users.add(1);
        users.add(2);
        users.add(10);
        userMapper.deleteBatchIds(users);
    }

    //多条件查询
    @Test
    public void testSelectByIdAndName(){
        User res = userMapper.selectByIdAndName(3, "xiu");
        System.out.println(res);
    }

    //多条件分页
    @Test
    public void testPage(){
        Page<UserVO> page = new Page<UserVO>(0,2);
        List<UserVO> res = userMapper.pageByParams(new Page<UserVO>(0, 2), 3, "xiu");
        System.out.println(res);
    }
    
    //queryWrapper分页
    @Test
    public void testPageQueryWrapper(){
        Page<User> page = new Page<User>(0,2);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 3).or().eq("name", "xiu");
        userMapper.selectPage(page, queryWrapper);
    }

    //List拼接成字符串
    @Test
    public void testListToString(){
        List<String> list = new ArrayList<>();
        list.add("周一");
        list.add("周二");
        String res = list.stream().collect(Collectors.joining(","));
        System.out.println(res);
    }
}
