package com.example.mptest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mptest.entity.PersonalInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonalInformationMapper extends BaseMapper<PersonalInformation> {
}
