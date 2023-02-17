package com.wjq.af.auth.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 权限认证配置
 *
 * @author yixihan
 * @date 2023/2/17 9:09
 */
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class AuthProp {
    
    /**
     * 前端控制器软件包
     */
    private String controllerPackage;
}
