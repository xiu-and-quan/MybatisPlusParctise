package com.example.mptest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mptest.controller.RedissionController;
import com.example.mptest.entity.User;
import com.example.mptest.enums.ChecklistStatusEnums;
import com.example.mptest.extendsTest.Animal;
import com.example.mptest.extendsTest.Animal1;
import com.example.mptest.mapper.UserMapper;
import com.example.mptest.pojo.vo.UserVO;
import com.example.mptest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpTestApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedissionController redissionController;

    @Test
    public void testupdate(){
        User u1 = userMapper.selectById(3);
        u1.setName("snow");
        userMapper.updateById(u1);
    }

    //redis
    @Autowired
    private RedisTemplate redisTemplate;


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

    //百万数据插入
    @Test
    public void saveBatchTest(){
        userService.InsertUsers();
    }

    //Comparable
    @Test
    public void compareTest(){
        int[] array = new int[]{5,4,3,2,1};
        Arrays.sort(array);
        System.out.println(array);
    }

    @Test
    public void redisAddTest() throws InterruptedException {
        //插入单条数据
        redisTemplate.opsForValue().set("key1", "我是新信息");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        //插入单条数据（存在有效期）
        System.out.println("-----------------");
        redisTemplate.opsForValue().set("key2", "这是一条会过期的信息", 1, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
        System.out.println(redisTemplate.hasKey("key2"));
        //检查key是否存在，返回boolean值
        System.out.println(redisTemplate.opsForValue().get("key2"));
        Thread.sleep(2000);
        System.out.println(redisTemplate.hasKey("key2"));//检查key是否存在，返回boolean值
        System.out.println(redisTemplate.opsForValue().get("key2"));
        System.out.println("-----------------");
    }

    @Test
    public void lockTest(){
        redissionController.lockTest();
    }

    @Test
    public void enumTest(){
        String[] res = {ChecklistStatusEnums.UNFINISHED.code()};
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void updateTest(){
        UpdateWrapper<User> updateWrapper = Wrappers.update();
        updateWrapper.lambda().set(User::getName,"xiu")
                .eq(User::getId,1);
        int res = userMapper.update(null,updateWrapper);
        System.out.println(res);
    }

    @Test
    public void findByIdTest(){
        User user = userMapper.selectById(1);
    }

    //copy null
    @Test
    public void copyTest(){
        User user = userMapper.selectById(10);
        UserVO userVO = new UserVO();
        if (userVO == null){
            System.out.println("查询不存在！");
        }else {
            BeanUtils.copyProperties(user,userVO);
        }
    }

    @Test
    public void lessAndEqTest(){
        List<User> userList = userMapper.selectLessAndEq(2);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectInTest(){
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            ids.add(i);
        }
        List<User> userList = userService.selectIn(ids);
        userList.forEach(System.out::println);
    }

    @Test
    /** 流的使用 List转成map **/
    public void toMapTest(){
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add(User.builder().id(i).name("xiu"+ i).build());

        }
        list.add(User.builder().id(1).name("quan").build());
        // 如果id有重复的就取第一个值，丢弃后面的
        Map<Integer, User> res = list.stream().collect(Collectors.toMap(User::getId, Function.identity(), (e1, e2) -> e1));
        /* 结果如下：
        * 1=User(id=1, name=xiu1)
          2=User(id=2, name=xiu2)
          3=User(id=3, name=xiu3)
          4=User(id=4, name=xiu4)
        * */
        Iterator<Map.Entry<Integer, User>> iterators = res.entrySet().iterator();
        while (iterators.hasNext()){
            System.out.println(iterators.next());
        }
    }

}
