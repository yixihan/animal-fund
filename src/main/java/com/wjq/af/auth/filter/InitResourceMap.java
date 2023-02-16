package com.wjq.af.auth.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import com.wjq.af.auth.annotation.RoleAccess;
import com.wjq.af.auth.cache.AuthRedisCacheService;
import com.wjq.af.enums.RoleEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author yixihan
 * @date 2023/2/16 14:50
 */
@Slf4j
@Component
public class InitResourceMap {
    
    @Resource
    private AuthRedisCacheService cacheService;
    
    private static final String PACKAGE_NAME = "com.wjq.af.controller";
    
    @PostConstruct
    public void initResourceMap () {
        // 清空
        cacheService.clearResource ();
        
        // 获取 controller 包下面的所有类
        Set<Class<?>> classes = ClassUtil.scanPackage (PACKAGE_NAME);
    
        for (Class<?> aClass : classes) {
            // 获取该类的 RoleAccess 注解
            RoleAccess classRoleAccess = aClass.getAnnotation (RoleAccess.class);
            // 获取该类的 RequestMapping 注解
            RequestMapping classRequestMapping = aClass.getAnnotation (RequestMapping.class);
        
            if (classRequestMapping == null) {
                continue;
            }
            // 获取请求路径
            String classPath = classRequestMapping.value ()[0];
            // 获取 RoleEnum
            RoleEnums[] classRoleEnums;
            if (classRoleAccess == null || ArrayUtil.isEmpty (classRoleAccess.value ())) {
                classRoleEnums = new RoleEnums[]{RoleEnums.USER};
            } else {
                classRoleEnums = classRoleAccess.value ();
            }
        
            // 获取该类的所有 public 方法
            for (Method method : ClassUtil.getPublicMethods (aClass)) {
                // 获取该方法的 RoleAccess 注解
                RoleAccess methonRoleAccess = method.getAnnotation (RoleAccess.class);
                // 获取该方法的 RequestMapping 注解
                String methodPath = getMethodPath (method);
            
                if (methodPath == null) {
                    continue;
                }
                // 获取请求路径
                methodPath = classPath + methodPath;
                
                // 获取 RoleEnum
                RoleEnums[] methodRoleEnums;
                if (methonRoleAccess == null || ArrayUtil.isEmpty (methonRoleAccess.value ())) {
                    methodRoleEnums = classRoleEnums;
                } else {
                    methodRoleEnums = methonRoleAccess.value ();
                }
            
                List<RoleEnums> roleList = CollUtil.toList (methodRoleEnums);
                cacheService.putResource (methodPath, roleList);
            }
        
        }
    
    
    }
    
    /**
     * 获取方法请求路径
     *
     * @param method 方法
     * @return 请求路径
     */
    private String getMethodPath (Method method) {
        RequestMapping requestMapping = method.getAnnotation (RequestMapping.class);
        PostMapping postMapping = method.getAnnotation (PostMapping.class);
        GetMapping getMapping = method.getAnnotation (GetMapping.class);
        DeleteMapping deleteMapping = method.getAnnotation (DeleteMapping.class);
        PutMapping putMapping = method.getAnnotation (PutMapping.class);
        PatchMapping patchMapping = method.getAnnotation (PatchMapping.class);
        
        if (requestMapping != null) {
            return requestMapping.value ()[0];
        }
        if (postMapping != null) {
            return postMapping.value ()[0];
        }
        if (getMapping != null) {
            return getMapping.value ()[0];
        }
        if (deleteMapping != null) {
            return deleteMapping.value ()[0];
        }
        if (putMapping != null) {
            return putMapping.value ()[0];
        }
        if (patchMapping != null) {
            return patchMapping.value ()[0];
        }
        return null;
    }
}
