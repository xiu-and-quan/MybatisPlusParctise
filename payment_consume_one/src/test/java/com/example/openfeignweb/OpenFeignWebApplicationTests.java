package com.example.openfeignweb;

import com.example.openfeignweb.controller.OpenFeignController;
import com.example.openfeignweb.openfeign.OpenfeignService;
import com.example.openfeignweb.pojo.dto.OpenFeignDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenFeignWebApplicationTests {

    @Autowired
    private OpenFeignController openFeignController;

    @Autowired
    private OpenfeignService openfeignService;

    @Test
    public void testOpenfeign(){
        System.out.println(openFeignController.findString(1));;
    }

    @Test
    public void findByBodyAndParam(){
        OpenFeignDTO openFeignDTO = new OpenFeignDTO();
        openFeignDTO.setName("xiu");
        List<String> res = openfeignService.findByBodyAndParam(1, openFeignDTO);
        res.forEach(System.out::println);
    }

    @Test
    public void testPrint(){
        System.out.println("打印");
    }

}
