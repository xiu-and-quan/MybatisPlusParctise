package com.example.mptest.extendsTest;

public abstract class Animal1 implements Animal{
    public boolean generate(){
        return isNeedGenBill();
    }
    public boolean isNeedGenBill(){
        return true;
    };
}
