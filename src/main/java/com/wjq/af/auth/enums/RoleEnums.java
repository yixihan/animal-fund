package com.wjq.af.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色枚举类
 *
 * @author yixihan
 * @date 2023/2/16 13:37
 */
@Getter
@AllArgsConstructor
public enum RoleEnums {
    
    /**
     * 超级管理员
     */
    ADMIN (1000000001L, "管理员"),
    
    /**
     * 管理员
     */
    VOLUNTEER (1000000002L, "志愿者"),
    
    /**
     * 用户
     */
    USER (1000000003L, "用户");
    
    
    /**
     * 角色 id
     */
    private final Long roleId;
    
    /**
     * 角色名
     */
    private final String roleName;
}
