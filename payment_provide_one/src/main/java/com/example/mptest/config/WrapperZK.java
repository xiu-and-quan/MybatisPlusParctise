package com.example.mptest.config;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * zookeeper配置文件
 */
@Data
@Component
/*获取配置文件的数据*/
@ConfigurationProperties(prefix = "curator")
public class WrapperZK {

    private int retryCount;

    private int elapsedTimeMs;

    private String connectString;

    private int sessionTimeoutMs;

    private int connectionTimeoutMs;

}
