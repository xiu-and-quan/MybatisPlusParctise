package com.example.mptest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mptest.entity.User;
import com.example.mptest.pojo.dto.UserListDTO;
import com.example.mptest.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper extends EasyBaseMapper<User> {

    void bathSaveHandWrite(List<User> users);

    void batchSave(List<User> users);

    User selectByIdAndName(int id, String name);

    /**
     多条件分页
     */
    List<UserVO> pageByParams(IPage<UserVO> page, int id, String name);

    List<User> selectLessAndEq(Integer id);

    List<User> mysqlInSet(Set<Integer> ids);

    //多表查询
    List<User> subQuery();


    User queryByClass(User user);

    List<User> queryByClaaContainsList(UserListDTO userListDTO);

    /** 返回值映射 **/
    List<UserVO> selecyMap(User user);

    /** 雪花算法插入 **/
    void inserBySnowFlower(User user);

    /** selectPage()重载 **/
    List<User> selectPage(IPage<User> iPage,Long id);
}
