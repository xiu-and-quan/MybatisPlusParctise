package com.example;

import com.example.IService.HelloSPI;

import java.util.ServiceLoader;

/**
 * @Author shizq18
 * @Date 2023/5/29
 * @Description
 */
public class SPIDemo {
    public static void main(String[] args) {
        Class<HelloSPI> service;
        try {
            Class<?> res = Class.forName("com.example.IService.HelloSPI");
            System.out.println(res.getName());
            Object res1 = res.newInstance();
            System.out.println(res1);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        ServiceLoader<HelloSPI> spiServiceLoader = ServiceLoader.load(HelloSPI.class);
        // 策略模式
        for (HelloSPI helloSpi:
             spiServiceLoader) {
            helloSpi.sayHello();
        }
    }
}
