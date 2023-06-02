/*
package com.example.mptest.config;

import com.alibaba.fastjson.JSON;
import com.example.mptest.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
*/
/**
 * Consumer配置文件
 *//*

@Configuration
public class Consumer {

    @KafkaListener(topics = "register")
    public void consume(String message) {
        System.out.println("接收到消息：" + message);
        User user = JSON.parseObject(message, User.class);
        System.out.println("正在为 " + user.getName() + " 办理注册业务...");
        System.out.println("注册成功");
    }
}
*/
