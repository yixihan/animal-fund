package com.wjq.af.auth.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单配置
 *
 * @author yixihan
 * @date 2023/2/16 14:06
 */
@Data
@Component
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoreUrlsProp {
    
    /**
     * 白名单列表
     */
    private List<String> urls = new ArrayList<> ();
}
