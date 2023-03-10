package com.wjq.af.controller.user;


import com.wjq.af.auth.annotation.RoleAccess;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.dto.request.user.ModifyUserDtoReq;
import com.wjq.af.dto.request.user.RegisterUserDtoReq;
import com.wjq.af.dto.request.user.RegisterVolunteerDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.service.user.UserService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 用户 OpenApi
 * </p>
 *
 * @author yixihan
 * @since 2023-02-15
 */
@Slf4j
@RoleAccess
@Validated
@RestController
@RequestMapping("/open/user")
@Api(tags = "用户 OpenApi")
public class UserController {
    
    @Resource
    private UserService service;
    
    @ApiOperation("获取登录用户信息")
    @GetMapping(value = "/info/cache", produces = "application/json")
    public JsonResponse<UserDtoResult> getCacheUserInfo() {
        return JsonResponse.ok (service.getCacheUserInfo ());
    }
    
    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info/id", produces = "application/json")
    public JsonResponse<UserDtoResult> getCacheUserInfo(@RequestParam("userId") Long userId) {
        return JsonResponse.ok (service.getUserInfo (userId));
    }
    
    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info/email", produces = "application/json")
    public JsonResponse<UserDtoResult> getCacheUserInfoByEmail(@RequestParam("email") String email) {
        return JsonResponse.ok (service.getUserInfoByEmail (email));
    }
    
    @ApiOperation("注册用户")
    @PostMapping(value = "/register/user", produces = "application/json")
    public void registerUser(@RequestBody @Valid RegisterUserDtoReq req) {
        // 参数校验
        validateRegisterUser (req);
        service.registerUser (req);
    }
    
    @ApiOperation("注册志愿者")
    @RoleAccess(value = RoleEnums.ADMIN)
    @PostMapping(value = "/register/volunteer", produces = "application/json")
    public void registerVolunteer(@RequestBody @Valid RegisterVolunteerDtoReq req) {
        // 参数校验
        validateRegisterUser (req);
        service.registerVolunteer (req);
    }
    
    @ApiOperation ("修改用户信息")
    @PostMapping(value = "/modify/info", produces = "application/json")
    public void modifyUserInfo (@RequestBody ModifyUserDtoReq req) {
        service.modifyUserInfo (req);
    }
    
    @ApiOperation ("登出账户")
    @PutMapping(value = "/logout", produces = "application/json")
    public void logout () {
        service.logout ();
    }
    
    @ApiOperation ("注销账户")
    @DeleteMapping(value = "/cancellation", produces = "application/json")
    public void cancellation () {
        service.cancellation ();
    }
    
    /**
     * 用户注册, 参数校验
     *
     * @param req 请求参数
     */
    private void validateRegisterUser(RegisterUserDtoReq req) {
        Assert.isTrue (ValidationUtil.validateMobile (req.getUserMobile ()));
        Assert.isTrue (ValidationUtil.validateEmail (req.getUserEmail ()));
        Assert.isTrue (ValidationUtil.validatePassword (req.getUserPassword ()));
        Assert.isTrue (ValidationUtil.validateUserName (req.getUserFullName ()));
        Assert.isTrue (ValidationUtil.validateUserName (req.getUserNickName ()));
        Assert.isTrue (ValidationUtil.validateIdCard (req.getUserIdCard ()));
    }
}
