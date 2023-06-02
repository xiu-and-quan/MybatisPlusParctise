package com.example.impl;

import com.example.IService.DubboSPI;

/**
 * @Author shizq18
 * @Date 2023/5/29
 * @Description
 */
public class ImageSPIImpl implements DubboSPI {
    @Override
    public void sayHello() {
        System.out.println("Image Hello");
    }
}
