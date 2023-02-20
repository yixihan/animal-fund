package com.wjq.af.auth.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import com.wjq.af.auth.annotation.RoleAccess;
import com.wjq.af.auth.cache.AuthRedisCacheService;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.auth.prop.AuthProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * 初始化各接口权限数据
 *
 * @author yixihan
 * @date 2023/2/16 14:50
 */
@Slf4j
@Component
public class InitResourceMap {
    
    @Resource
    private AuthRedisCacheService cacheService;
    
    @Resource
    private AuthProp prop;
    
    @PostConstruct
    public void initResourceMap() {
        // 清空
        cacheService.clearResource ();
        
        // 获取 controller 包下面的所有类
        Set<Class<?>> classes = ClassUtil.scanPackage (prop.getControllerPackage ());
        
        // 初始化该类所有接口的权限数据
        log.info ("开始初始化各接口权限数据...");
        for (Class<?> aClass : classes) {
            initClassResourceMap (aClass);
        }
        log.info ("接口权限数据初始化完毕~");
    }
    
    /**
     * 初始化指定类所有接口的权限数据
     *
     * @param aClass 指定类 Class 对象
     */
    private void initClassResourceMap(Class<?> aClass) {
        // 获取该类的 RoleAccess 注解
        RoleAccess classRoleAccess = aClass.getAnnotation (RoleAccess.class);
        // 获取该类的 RequestMapping 注解
        RequestMapping classRequestMapping = aClass.getAnnotation (RequestMapping.class);
        
        if (classRequestMapping == null) {
            return;
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
            initMethodResourceMap (classPath, classRoleEnums, method);
        }
    }
    
    /**
     * 初始化指定接口的权限数据
     *
     * @param classPath      接口所在类的请求路径
     * @param classRoleEnums 接口所在类的权限数据
     * @param method         指定接口
     */
    private void initMethodResourceMap(String classPath, RoleEnums[] classRoleEnums, Method method) {
        // 获取该方法的 RoleAccess 注解
        RoleAccess methonRoleAccess = method.getAnnotation (RoleAccess.class);
        // 获取该方法的 RequestMapping 注解
        String methodPath = getMethodPath (method);
        
        if (methodPath == null) {
            return;
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
        log.info ("资源路径 : {}, 权限为 : {}", methodPath, roleList);
        cacheService.putResource (methodPath, roleList);
    }
    
    /**
     * 获取方法请求路径
     *
     * @param method 方法
     * @return 请求路径
     */
    private String getMethodPath(Method method) {
        // 六种常用接口注释
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
