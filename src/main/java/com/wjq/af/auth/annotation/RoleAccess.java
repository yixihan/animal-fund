package com.wjq.af.auth.annotation;

import com.wjq.af.enums.RoleEnums;

import java.lang.annotation.*;

/**
 * 权限注解
 * <p>
 * 可加在类/方法上
 * </p>
 *
 * @author yixihan
 * @date 2023/2/16 14:40
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleAccess {
    
    RoleEnums[] value() default {};
}
