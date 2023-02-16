package com.wjq.af.service.auth;

import com.wjq.af.dto.request.auth.AuthDtoReq;
import com.wjq.af.dto.response.auth.AuthDtoResult;

/**
 * description
 *
 * @author yixihan
 * @date 2023/2/15 17:17
 */
public interface AuthService {
    
    /**
     * 登录
     *
     * @param dtoReq 请求参数
     * @return {@link AuthDtoResult}
     */
    AuthDtoResult login(AuthDtoReq dtoReq);
}
