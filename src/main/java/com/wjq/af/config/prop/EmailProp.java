package com.wjq.af.config.prop;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮件 配置参数
 *
 * @author yixihan
 * @date 2023/2/17 14:47
 */
@Getter
@Component
public class EmailProp {
    
    @Value("${thirdpart.email.send-email}")
    private String sendEmail;
    
    @Value("${thirdpart.email.title}")
    private String title;
    
    @Value("${thirdpart.email.update-password-key}")
    private String updatePasswordKey;
    
    @Value("${thirdpart.email.common-key}")
    private String commonKey;
    
}
