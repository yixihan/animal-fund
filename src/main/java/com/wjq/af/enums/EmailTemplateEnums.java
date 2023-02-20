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
     * 审核失败模板
     */
    EXAMINE_FAIL (1000000002L, "审核失败模板"),
    
    /**
     * 审核成功模板
     */
    EXAMINE_SUCCESS (1000000003L, "审核成功模板"),
    
    /**
     * 举报失败模板
     */
    REPORT_FAIL (1000000004L, "举报失败模板"),
    
    /**
     * 举报成功模板
     */
    REPORT_SUCCESS (1000000005L, "举报成功模板"),
    
    /**
     * 通用模板
     */
    COMMON (1000000006L, "通用模板");
    
    /**
     * 模板 id
     */
    private final Long id;
    
    /**
     * 描述
     */
    private final String description;
}
