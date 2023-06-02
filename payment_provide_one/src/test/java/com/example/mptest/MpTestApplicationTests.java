package com.example.mptest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mptest.controller.UserController;
import com.example.mptest.entity.User;
import com.example.mptest.enums.ChecklistStatusEnums;
import com.example.mptest.mapper.UserMapper;
import com.example.mptest.pojo.dto.TestDTO;
import com.example.mptest.pojo.dto.UserListDTO;
import com.example.mptest.pojo.dto.UserString;
import com.example.mptest.pojo.problemAboutExtends.Animal;
import com.example.mptest.pojo.problemAboutExtends.Dog;
import com.example.mptest.pojo.vo.UserVO;
import com.example.mptest.service.UserService;
import com.example.mptest.until.BeanObjectCopyUtils;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanCopier;
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
    private UserController userController;

    @Test
    public void testupdate(){
        User u1 = userMapper.selectById(3);
        u1.setName("snow");
        userMapper.updateById(u1);
    }

    //批量插入
    @Test
    public void testBatchInsert(){
        User u1 = new User();
        u1.setName("xiu");
        User u2 = new User();
        u2.setName("dongMa");
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

    @Test
    public void extendsTest(){
        User user = new User();
        UserString userString = new UserString();
        user.setId(new Long(1));
        user.setName("xiu");
        BeanObjectCopyUtils.copyObject(userString,user);
        System.out.println(userString);
    }

    @Test
    public void selecyMapTest(){
        User user = new User();
        user.setId(new Long(1));
        List<UserVO> res = userMapper.selecyMap(user);
        res.forEach(System.out::println);
    }

    //雪花算法生成主键
    @Test
    public void reducePrimaryBySnowFlower(){
        User user = new User();
        user.setName("xiu");
        System.out.println(user.getId());
        userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void selectPageTest(){
        List<User> data = userMapper.selectPage(new Long(1));
        data.forEach(System.out::println);
    }

    @Test
    public void removeIterator() {
        List<TestDTO> users = new ArrayList<>();
        users.add(new TestDTO(1,"xiu","N"));
        users.add(new TestDTO(1,"xiu"));
        users.add(new TestDTO(1,"xiu"));
        Iterator<TestDTO> iterator = users.iterator();
        while (iterator.hasNext()){
            if ("N".equals(iterator.next().state)) {
                iterator.remove();
            }
        }
        System.out.println(users.size());
    }

    @Value("${test.type}")
    boolean type1;

    @Test
    public void valueTest() {
        if (type1) {
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }

}
