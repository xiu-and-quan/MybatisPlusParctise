package com.example.mptest.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.example.mptest.savaBatch.EasySqlInjector;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MybatisPlusConfig {
    //需要配拦截器
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public EasySqlInjector easySqlInjector() {
        return new EasySqlInjector();
    }

    /**
     * 自动填充处理器，自动填充创建时间、修改时间、创建人和修改人
     * strict严格填充默认规则：如果有值（非null），则不会填充覆盖
     */
    private String currentUserName = "shizq18";
    @Bean
    public MetaObjectHandler myMetaObjectHandler(){
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                strictInsertFill(metaObject, "dateTimeCreated", LocalDateTime.class, LocalDateTime.now());
                strictInsertFill(metaObject, "dateTimeModified", LocalDateTime.class, LocalDateTime.now());

                if (StringUtils.isNotBlank(currentUserName)) {
                    this.strictInsertFill(metaObject, "userCreated", String.class, currentUserName);
                    this.strictInsertFill(metaObject, "userModified", String.class, currentUserName);
                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                strictUpdateFill(metaObject, "dateTimeModified", LocalDateTime.class, LocalDateTime.now());
                if (StringUtils.isNotBlank(currentUserName)) {
                    this.strictUpdateFill(metaObject, "userModified", String.class, currentUserName);
                }
            }
        };
    }
}