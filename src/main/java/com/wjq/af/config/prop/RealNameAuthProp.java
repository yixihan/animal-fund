package com.wjq.af.config.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 实名认证 配置参数
 *
 * @author yixihan
 * @date 2023/2/20 13:40
 */
@Data
@Component
public class RealNameAuthProp {
    
    @Value("${thirdpart.auth.url}")
    private String url;
    
    @Value("${thirdpart.auth.app-code}")
    private String appCode;
}
