package com.wjq.af.auth;

import cn.hutool.core.bean.BeanUtil;
import com.wjq.af.auth.cache.AuthRedisCacheService;
import com.wjq.af.dto.response.auth.AuthDtoResult;
import com.wjq.af.dto.response.user.RoleDtoResult;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.pojo.user.User;
import com.wjq.af.service.user.RoleService;
import com.wjq.af.service.user.UserService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.JwtUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Token 认证授权 服务类
 *
 * @author yixihan
 * @date 2023/2/16 11:17
 */
@Service
public class TokenService {
    
    @Resource
    private UserService userService;
    
    @Resource
    private RoleService roleService;
    
    @Resource
    private AuthRedisCacheService cacheService;
    
    private static final String USER_ID = "userId";
    
    /**
     * 认证校验
     *
     * @param token jwtToken
     * @return {@link AuthDtoResult}
     * @throws  BizException 登录失败则抛出 (账户不存在, 密码错误, token 过期)
     */
    public AuthDtoResult authentication (String token) throws BizException {
        // 从 token 种获取 userId
        Long userId = JwtUtils.analysis (token, USER_ID, Long.class);
    
        // 从数据库种获取 user
        User user = userService.getById (userId);
    
        // 账户不存在
        Assert.isNotNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
        
        // token 过期
        Assert.isTrue (JwtUtils.validateDate (token), BizCodeEnum.TOKEN_EXPIRED);
        
        // token 错误
        Assert.isTrue (JwtUtils.validateToken (token, user.getUserPassword ()), BizCodeEnum.TOKEN_ERR);
        
        AuthDtoResult authInfo = new AuthDtoResult (
                BeanUtil.toBean (user, UserDtoResult.class),
                roleService.getUserRoleList (userId),
                token
        );
        
        // 存储进 redis
        cacheService.put (token, authInfo);
        
        return authInfo;
    }
    
    /**
     * 权限校验
     *
     * @param token jwtToken
     * @param requestUri 请求路径
     * @return true : 可以访问 | false : 不可访问
     */
    public Boolean authorization (String token, String requestUri) {
        // 从 redis 中获取登录用户权限信息
        List<RoleDtoResult> roleList = cacheService.get (token).getRoleList ();
        // 从 redis 中获取资源路径权限信息
        final List<Long> roleIdList = cacheService.getResource (requestUri);
        
        return roleList.stream ()
                .map (RoleDtoResult::getId)
                .anyMatch (roleIdList::contains);
    }
    
    /**
     * 登出
     *
     * @param token jwtToken
     */
    public void logout (String token) {
        cacheService.del (token);
    }
}
