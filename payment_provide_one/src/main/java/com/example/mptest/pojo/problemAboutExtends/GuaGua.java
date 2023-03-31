package com.example.mptest.pojo.problemAboutExtends;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GuaGua {

    public String getLoud(){
        return "guagua";
    }
}
