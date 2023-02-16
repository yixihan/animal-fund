package com.wjq.af.controller.user;


import com.wjq.af.auth.annotation.RoleAccess;
import com.wjq.af.auth.cache.AuthRedisCacheService;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.enums.RoleEnums;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-02-15
 */
@Api(tags = "用户 OpenApi")
@RoleAccess
@RestController
@RequestMapping("/open/user")
public class UserController {
    
    @Resource
    private AuthRedisCacheService cacheService;
    
    @Resource
    private HttpServletRequest request;
    
    @ApiOperation ("获取登录用户信息")
    @RoleAccess(RoleEnums.ADMIN)
    @GetMapping(value = "/info/cache", produces = "application/json")
    public UserDtoResult getUserInfo () {
        
        return cacheService.get (request.getHeader ("Jwt-Token")).getUser ();
    }

}
