package com.example.mptest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.mptest.entity.User;
import com.example.mptest.pojo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    void bathSaveHandWrite(List<User> users);

    void batchSave(List<User> users);

    User selectByIdAndName(int id, String name);

    /**
     多条件分页
     */
    List<UserVO> pageByParams(IPage<UserVO> page, int id, String name);
}
