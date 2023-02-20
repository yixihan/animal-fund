package com.wjq.af.config.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 验证码 配置参数
 *
 * @author yixihan
 * @date 2023/2/17 14:40
 */
@Data
@Component
public class CodeProp {
    
    @Value("${thirdpart.code.length}")
    private Integer codeLen;
    
    @Value("${thirdpart.code.time-out}")
    private Integer timeOut;
    
    // **** redis key **** //
    @Value("${thirdpart.code.common-key}")
    private String commonKey;
}
