package com.wjq.af.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 救援状态 枚举类
 *
 * @author yixihan
 * @date 2023/2/22 9:34
 */
@Getter
@AllArgsConstructor
public enum RescueStatusEnums {
    
    /**
     * 未开始
     */
    UN_DO ("UN_DO", "未开始"),
    
    /**
     * 审核中
     */
    EXAMINING ("EXAMINING", "审核中"),
    
    /**
     * 募款中
     */
    FUNDRAISING ("FUNDRAISING", "募款中"),
    
    /**
     * 救援中
     */
    RESCUING ("RESCUING", "救援中"),
    
    /**
     * 救援成功
     */
    SUCCESS ("SUCCESS", "救援成功"),
    
    /**
     * 救援失败
     */
    FAIL ("FAIL", "救援失败");
    
    private final String value;
    
    private final String description;
}
