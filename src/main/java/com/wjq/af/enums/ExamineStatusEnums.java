package com.wjq.af.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态 枚举类
 *
 * @author yixihan
 * @date 2023/2/17 14:12
 */
@Getter
@AllArgsConstructor
public enum ExamineStatusEnums {
    
    /**
     * 待审核
     */
    UN_EXAMINE ("UN_EXAMINE", "待审核"),
    
    /**
     * 审核通过
     */
    EXAMINE_SUCCESS ("EXAMINE_SUCCESS", "审核通过"),
    
    /**
     * 审核失败
     */
    EXAMINE_FAIL ("EXAMINE_FAIL", "审核失败");
    
    /**
     * 审核状态
     */
    private final String value;
    
    /**
     * 描述
     */
    private final String description;
}
