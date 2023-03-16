package com.example.mptest.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class RedissionController {
    @Autowired
    private RedissonClient redisson;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/lock/test")
    public void lockTest() {
        String lockKey = UUID.randomUUID().toString();
        RLock lock = redisson.getLock(lockKey);       //获取锁
        try {
            lock.lock();    //上锁
            log.info("锁已开启");
            synchronized (this) {
                if (redisTemplate.opsForValue().get("product") == null) {
                    log.error("商品不存在！");
                } else {
                    //获取当前库存
                    int stock = Integer.parseInt(redisTemplate.opsForValue().get("product").toString());
                    if (stock > 0) {
                        int realStock = stock - 1;
                        //更新库存
                        redisTemplate.opsForValue().set("product", realStock + "");
                        log.info("库存当前为：" + realStock);
                    } else {
                        log.warn("扣减失败，库存不足！");
                    }
                }
            }
        } catch (Exception e) {
            log.warn("系统错误，稍后重试");
        } finally {
            lock.unlock();    //删除锁
            log.info("锁已关闭");
        }
    }
}