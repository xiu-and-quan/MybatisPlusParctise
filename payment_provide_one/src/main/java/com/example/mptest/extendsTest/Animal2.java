package com.example.mptest.extendsTest;

import org.springframework.stereotype.Service;

@Service
public class Animal2 extends Animal1{
    @Override
    public boolean isNeedGenBill() {
        return false;
    }
}
