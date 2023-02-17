package com.wjq.af.config.prop;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * SMS 配置参数
 *
 * @author yixihan
 * @date 2023/2/17 15:05
 */
@Getter
@Component
public class SMSProp {
    
    @Value("${thirdpart.mobile.secretId}")
    private String secretId;
    
    @Value("${thirdpart.mobile.secretKey}")
    private String secretKey;
    
    @Value("${thirdpart.mobile.smsSdkAppId}")
    private String smsSdkAppId;
    
    @Value("${thirdpart.mobile.signName}")
    private String signName;
    
    // **** redis key **** //
    
    @Value("${thirdpart.mobile.login-key}")
    private String loginKey;
    
    @Value("${thirdpart.mobile.register-key}")
    private String registerKey;
    
    @Value("${thirdpart.mobile.update-password-key}")
    private String updatePasswordKey;
    
    @Value("${thirdpart.mobile.common-key}")
    private String commonKey;
}
