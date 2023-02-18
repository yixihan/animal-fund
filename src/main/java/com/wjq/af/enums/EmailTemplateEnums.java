package com.wjq.af.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 邮件模板 枚举类
 *
 * @author yixihan
 * @date 2023/2/17 15:59
 */
@Getter
@AllArgsConstructor
public enum EmailTemplateEnums {
    
    /**
     * 重置密码模板
     */
    RESET_PASSWORD (1000000001L, "重置密码模板"),
    
    /**
     * 审核模板
     */
    EXAMINE (1000000002L, "审核模板"),
    
    /**
     * 举报模板
     */
    REPORT (1000000003L, "举报模板"),
    
    /**
     * 通用模板
     */
    COMMON (1000000004L, "通用模板");
    
    /**
     * 模板 id
     */
    private final Long id;
    
    /**
     * 描述
     */
    private final String description;
}
