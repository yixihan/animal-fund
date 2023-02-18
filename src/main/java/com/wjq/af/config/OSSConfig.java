package com.wjq.af.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wjq.af.config.prop.OssProp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * OSS 配置类
 *
 * @author yixihan
 * @date 2023/2/18 11:43
 */
@Configuration
public class OSSConfig {
    
    @Resource
    private OssProp prop;
    
    @Bean
    public OSS oss() {
        return new OSSClientBuilder ()
                .build (prop.getEndpoint (),
                        prop.getAccessKey (),
                        prop.getSecretKey ()
                );
    }
}
