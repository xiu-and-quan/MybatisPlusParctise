package com.example.openfeignweb.controller;

import com.example.openfeignweb.openfeign.UserFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenFeignController {

    private UserFeign userFeign;

    @GetMapping("/findById")
    public String openFeignTest(@RequestParam Integer id){
        return userFeign.testOpenfeign(1);
    }
}
