package com.wjq.af.config.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * oss 配置参数
 *
 * @author yixihan
 * @date 2023/2/17 14:47
 */
@Data
@Component
public class OssProp {
    
    @Value("${thirdpart.oss.access-key}")
    private String accessKey;
    
    @Value("${thirdpart.oss.secret-key}")
    private String secretKey;
    
    @Value("${thirdpart.oss.endpoint}")
    private String endpoint;
    
    @Value("${thirdpart.oss.bucket-name}")
    private String bucketName;
    
    @Value("${thirdpart.oss.host}")
    private String host;
}
