package com.example.openfeignweb.controller;

import com.example.openfeignweb.openfeign.OpenfeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenFeignController {

    @Autowired
    private OpenfeignService openfeignService;
    @GetMapping("/findOpenfeign")
    public String findString(@RequestParam Integer id){
        return openfeignService.testOpenfeign(id);
    }
}
