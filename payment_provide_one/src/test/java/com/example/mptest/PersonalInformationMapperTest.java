package com.example.mptest;

import com.example.mptest.entity.PersonalInformation;
import com.example.mptest.mapper.PersonalInformationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalInformationMapperTest {

    @Autowired
    private PersonalInformationMapper personalInformationMapper;

    /**
     * 表字段自动插入
     */
    @Test
    public void automaticInsetTest(){
        PersonalInformation person = new PersonalInformation();
        person.setName("xiu");
        int count = personalInformationMapper.insert(person);
        System.out.println(person.getId());
    }

    @Test
    private void testQuickSort(){
       
    }
}
