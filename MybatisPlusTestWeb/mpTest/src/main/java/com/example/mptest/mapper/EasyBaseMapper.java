package com.example.mptest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

public interface EasyBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入 百万数据
     * @param entityList
     * @return
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}
