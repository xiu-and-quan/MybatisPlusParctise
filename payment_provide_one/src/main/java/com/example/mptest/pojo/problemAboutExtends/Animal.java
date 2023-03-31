package com.example.mptest.pojo.problemAboutExtends;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
@Component
public class Animal {

    private GuaGua guaGua;
    public String getName(){
        System.out.println(guaGua.getLoud());
        return "Animal";
    }
}
