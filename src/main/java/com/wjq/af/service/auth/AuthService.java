package com.wjq.af.service.auth;

import com.wjq.af.dto.request.auth.AuthDtoReq;
import com.wjq.af.dto.request.auth.ResetPasswordDtoReq;
import com.wjq.af.dto.response.auth.AuthDtoResult;

/**
 * 认证授权 服务类
 *
 * @author yixihan
 * @date 2023/2/15 17:17
 */
public interface AuthService {
    
    /**
     * 登录
     *
     * @param req 请求参数
     * @return {@link AuthDtoResult}
     */
    AuthDtoResult login(AuthDtoReq req);
    
    /**
     * 重置密码
     *
     * @param req 请求参数
     */
    void resetPassword(ResetPasswordDtoReq req);
}
