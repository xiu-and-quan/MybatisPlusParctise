package com.example.IService;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author shizq18
 * @Date 2023/5/29
 * @Description DubboSPI
 */
@SPI
public interface DubboSPI {
    void sayHello();
}
