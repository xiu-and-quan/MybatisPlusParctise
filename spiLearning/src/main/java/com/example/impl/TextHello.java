package com.example.impl;

import com.example.IService.HelloSPI;

/**
 * @Author shizq18
 * @Date 2023/5/29
 * @Description HelloSPI接口实现类2
 */
public class TextHello implements HelloSPI {
    @Override
    public void sayHello() {
        System.out.println("Hello Text");
    }
}
