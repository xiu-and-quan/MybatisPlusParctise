package com.example.mptest.until;

import com.example.mptest.mapper.MyMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchInsertUtil<T> {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public void batchSava(List<T> list){
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            MyMapper myMapper = sqlSession.getMapper(MyMapper.class);
            // Here is an example of how to use getMapper() method:
            for (T item : list) {
                myMapper.insert(item);
            }
            sqlSession.commit();
        }
    }

}