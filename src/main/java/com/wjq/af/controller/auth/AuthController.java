package com.wjq.af.controller.auth;

import com.wjq.af.dto.request.auth.AuthDtoReq;
import com.wjq.af.dto.request.auth.ResetPasswordDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.auth.AuthDtoResult;
import com.wjq.af.service.auth.AuthService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * 认证授权 OpenApi
 *
 * @author yixihan
 * @date 2023/2/15 17:04
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
@Api(tags = "认证授权 OpenApi")
public class AuthController {
    
    @Resource
    private AuthService service;
    
    @ApiOperation("用户登录")
    @PostMapping(value = "/login", produces = "application/json")
    public JsonResponse<AuthDtoResult> login(@RequestBody @Valid AuthDtoReq req) {
        // 参数校验
        Assert.isTrue (ValidationUtil.validateMobile (req.getMobile ()));
        Assert.isTrue (ValidationUtil.validatePassword (req.getPassword ()));
        
        return JsonResponse.ok (service.login (req));
    }
    
    @ApiOperation("重置密码")
    @PostMapping(value = "/reset/password", produces = "application/json")
    public void resetPassword(@RequestBody @Valid ResetPasswordDtoReq req) {
        // 参数校验
        Assert.isTrue (ValidationUtil.validateEmail (req.getEmail ()));
        Assert.isTrue (ValidationUtil.validatePassword (req.getPassword ()));
        
        service.resetPassword (req);
    }
    
    
}
