package com.wjq.af.controller;

import com.wjq.af.dto.request.auth.AuthDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.auth.AuthDtoResult;
import com.wjq.af.service.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 认证授权 OpenApi
 *
 * @author yixihan
 * @date 2023/2/15 17:04
 */
@Api(tags = "认证授权 OpenApi")
@RequestMapping("/auth")
@RestController
public class AuthController {
    
    @Resource
    private AuthService service;
    
    @ApiOperation ("登录")
    @PostMapping(value = "/login", produces = "application/json")
    public JsonResponse<AuthDtoResult> login (@RequestBody AuthDtoReq dtoReq) {
        // 参数校验
        
        return JsonResponse.ok (service.login (dtoReq));
    }
    
    
}
