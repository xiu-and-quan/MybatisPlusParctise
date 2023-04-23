package com.example.mptest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mptest.controller.UserController;
import com.example.mptest.entity.User;
import com.example.mptest.enums.ChecklistStatusEnums;
import com.example.mptest.enums.Month;
import com.example.mptest.enums.Weekday;
import com.example.mptest.mapper.UserMapper;
import com.example.mptest.pojo.dto.UserListDTO;
import com.example.mptest.pojo.vo.UserVO;
import com.example.mptest.service.UserService;
import com.example.mptest.until.BatchInsertUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
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
    private UserController userController;

    @Test
    public void testupdate(){
        User u1 = userMapper.selectById(3);
        u1.setName("snow");
        userMapper.updateById(u1);
    }

    //批量插入
    /*@Test
    public void testBatchInsert(){
        User u1 = new User();
        u1.setName("xiu");
        User u2 = new User();
        u2.setName("dongMa");
        List<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        userMapper.batchSave(users);
    }*/

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
    /*@Test
    public void saveBatchTest(){
        userService.InsertUsers();
    }*/

    //Comparable
    @Test
    public void compareTest(){
        int[] array = new int[]{5,4,3,2,1};
        Arrays.sort(array);
        System.out.println(array);
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
            list.add(User.builder().id(new Long(i)).name("xiu"+ i).build());

        }
        list.add(User.builder().id(new Long(1)).name("quan").build());
        // 如果id有重复的就取第一个值，丢弃后面的
        Map<Long, User> res = list.stream().collect(Collectors.toMap(User::getId, Function.identity(), (e1, e2) -> e1));
        /* 结果如下：
        * 1=User(id=1, name=xiu1)
          2=User(id=2, name=xiu2)
          3=User(id=3, name=xiu3)
          4=User(id=4, name=xiu4)
        * */
        Iterator<Map.Entry<Long, User>> iterators = res.entrySet().iterator();
        while (iterators.hasNext()){
            System.out.println(iterators.next());
        }
    }

    @Test
    public void selectInSetTest(){
        Set<Integer> ids = new HashSet<>();
        for (int i = 1; i < 6; i++) {
            ids.add(i);
        }
        List<User> userList = userService.selectInSet(ids);
        userList.forEach(System.out::println);
    }

    @Test
    public void mysqlInSetTest(){
        Set<Integer> ids = new HashSet<>();
        for (int i = 1; i < 6; i++) {
            ids.add(i);
        }
        List<User> userList = userMapper.mysqlInSet(ids);
        userList.forEach(System.out::println);
    }

    @Test
    public void subQueryTest(){
        List<User> userList = userMapper.subQuery();
        userList.forEach(System.out::println);
    }

    @Test
    public void findFirstElementTest(){
        User user = userService.findFirstElement(1);
        System.out.println(user);
    }

    @Test
    public void queryByClassTest(){
        User user = User.builder().id(new Long(1)).name("xiu").build();
        User res = userMapper.queryByClass(user);
        System.out.println(res);
    }

    @Test
    public void queryByClaaContainsListTest(){
        UserListDTO userListDTO = new UserListDTO();
        userListDTO.setName("xiu");
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            ids.add(i);

        }
        userListDTO.setIds(ids);
        List<User> res = userMapper.queryByClaaContainsList(userListDTO);
        res.forEach(System.out::println);
    }

    @Test
    public void findPropertyByqueryWrapperTest(){
        List<UserVO> res = userService.findPropertyByqueryWrapper();
        res.forEach(System.out::println);
    }

    /*@Test
    public void extendsTest(){
        User user = new User();
        UserString userString = new UserString();
        user.setId(new Long(1));
        user.setName("xiu");
        BeanObjectCopyUtils.copyObject(userString,user);
        System.out.println(userString);
    }*/

    /*@Test
    public void selecyMapTest(){
        User user = new User();
        user.setId(new Long(1));
        List<UserVO> res = userMapper.selecyMap(user);
        res.forEach(System.out::println);
    }*/

    //雪花算法生成主键
    /*@Test
    public void reducePrimaryBySnowFlower(){
        User user = new User();
        user.setName("xiu");
        System.out.println(user.getId());
        userMapper.insert(user);
        System.out.println(user.getId());
    }*/

    @Test
    public void selectPageTest(){
        Page<User> page = new Page(0,3);
        List<User> data = userMapper.selectPage(page,new Long(1));
        data.forEach(System.out::println);
    }

    @Autowired
    private BatchInsertUtil batchInsert;

    /**
     * 批量插入
     */
    @Test
    public void saveBatchTestByUntil() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            users.add(User.builder().name("quan"+i).build());
        }
        long stime = System.currentTimeMillis();
        // 执行时间（1s）
        batchInsert.batchSava(users);
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("执行时长：%d 毫秒.", (etime - stime));
        //1万 9317毫秒
        //10万
    }

    /**
     * for循环插入
     */
    @Test
    public void forInsertTest(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            users.add(User.builder().name("quan"+i).build());
        }
        long stime = System.currentTimeMillis();
        // 执行时间（1s）
        for (User u:
             users) {
            userMapper.insert(u);
        }
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("执行时长：%d 毫秒.", (etime - stime));
        //1万 12925 毫秒
        //10万 125415
    }

    /**
     * 动态sql语句插入
     */
    @Test
    public void insetForEachxmlTest(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            users.add(User.builder().name("quan"+i).build());
        }
        long stime = System.currentTimeMillis();
        // 执行时间（1s）
        userMapper.batchSave(users);
        // 结束时间
        long etime = System.currentTimeMillis();
        // 计算执行时间
        System.out.printf("执行时长：%d 毫秒.", (etime - stime));
        //1万442 毫秒
        //10万 奔溃
    }

    @Test
    public void orInQueryWrapperTest(){
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.lambda().eq(User::getName,"quan2").or().eq(User::getName,"quan1").eq(User::getId,"1641709211698122755");
        List<User> res = userMapper.selectList(queryWrapper);
        res.forEach(System.out::println);
    }

    /**
     * 枚举类的使用
     */
    @Test
    public void enumWeekTest(){
        Weekday monday = Weekday.MONDAY;
        Weekday tuesday = Weekday.TUESDAY;

        System.out.println("Monday is " + monday.getValue());
        System.out.println("Tuesday is " + tuesday.getValue());
    }

    /**
     * QueryWrapper.select返回指定字段用自定义类接收
     */
    @Test
    public void queryWrapperSelectest(){
        User res = userMapper.selectById(new Long("1641709211576487938"));
        System.out.println(res);
    }

    /**
     * set可以设置null吗
     */
    @Test
    public void setNullTest(){
        String name = null;
        User user = User.builder().name("xiu").build();
        user.setName(name);
        System.out.println(user.getName());
    }

    /**
     * 测试枚举类类型
     */
    @Test
    public void enumClassTest(){
        Month december = Month.DECEMBER;
        System.out.println(december);
    }

}
