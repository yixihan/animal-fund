package com.wjq.af.service.auth.impl;

import cn.hutool.core.util.RandomUtil;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.auth.AuthDtoReq;
import com.wjq.af.dto.request.auth.ResetPasswordDtoReq;
import com.wjq.af.dto.request.thirdpart.EmailValidateDtoReq;
import com.wjq.af.dto.response.auth.AuthDtoResult;
import com.wjq.af.enums.EmailTemplateEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.pojo.user.User;
import com.wjq.af.service.auth.AuthService;
import com.wjq.af.service.thirdpart.EmailService;
import com.wjq.af.service.user.UserService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.JwtUtil;
import com.wjq.af.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证授权 服务实现类
 *
 * @author yixihan
 * @date 2023/2/15 17:17
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    
    @Resource
    private UserService userService;
    
    @Resource
    private TokenService tokenService;
    
    @Resource
    private EmailService emailService;
    
    @Override
    public AuthDtoResult login(AuthDtoReq req) {
        // 通过用户手机号查询用户
        User user = userService.lambdaQuery ()
                .eq (User::getUserMobile, req.getMobile ())
                .one ();
        
        // 断言 user != null
        Assert.notNull (user, new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND));
        
        // 加密用户输入的密码
        String md5Password = MD5Util.md5 (req.getPassword (), user.getUserSalt ());
        
        // 将加密后的密码存入 user
        user.setUserPassword (md5Password);
        
        // 生成 JwtToken
        Map<String, Object> payload = new HashMap<> (16);
        payload.put ("userId", user.getId ());
        payload.put ("userName", user.getUserNickName ());
        String token = JwtUtil.createJwtToken (user.getUserPassword (), payload);
        
        // 登录
        return tokenService.authentication (token);
    }
    
    @Override
    public void resetPassword(ResetPasswordDtoReq req) {
        // 验证码校验
        EmailValidateDtoReq emailReq = new EmailValidateDtoReq ();
        emailReq.setCode (req.getCode ());
        emailReq.setEmail (req.getEmail ());
        emailReq.setEmailType (EmailTemplateEnums.RESET_PASSWORD.name ());
        emailService.validate (emailReq);
        
        // 通过用户邮箱查询用户
        User user = userService.lambdaQuery ()
                .eq (User::getUserEmail, req.getEmail ())
                .one ();
    
        // 断言 user != null
        Assert.notNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
        
        // 密码加密
        String salt = RandomUtil.randomString (10);
        String password = MD5Util.md5 (req.getPassword (), salt);
        user.setUserPassword (password);
        user.setUserSalt (salt);
        
        // 更新数据库
        Assert.isTrue (userService.updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
}
