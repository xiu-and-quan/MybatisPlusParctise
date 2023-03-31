package com.example.mptest.pojo.problemAboutExtends;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Dog extends Animal{

    public Dog(GuaGua guaGua) {
        super(guaGua);
    }

    @Override
    public String getName(){
        return "Dog";
    }
}
