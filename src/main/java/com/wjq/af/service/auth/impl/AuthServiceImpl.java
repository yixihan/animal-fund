package com.wjq.af.service.auth.impl;

import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.auth.AuthDtoReq;
import com.wjq.af.dto.response.auth.AuthDtoResult;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.pojo.user.User;
import com.wjq.af.service.auth.AuthService;
import com.wjq.af.service.user.UserService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.JwtUtils;
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
    
    @Override
    public AuthDtoResult login(AuthDtoReq req) {
        User user = userService.lambdaQuery ()
                .eq (User::getUserMobile, req.getMobile ())
                .one ();
        
        Assert.notNull (user, new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND));
        
        // 加密用户输入的密码
        String md5Password = MD5Util.md5 (req.getPassword (), user.getUserSalt ());
        
        // 将加密后的密码存入 user
        user.setUserPassword (md5Password);
        
        // 生成 JwtToken
        Map<String, Object> payload = new HashMap<> (16);
        payload.put ("userId", user.getId ());
        payload.put ("userName", user.getUserNickName ());
        String token = JwtUtils.createJwtToken (user.getUserPassword (), payload);
        
        // 登录
        return tokenService.authentication (token);
    }
}
