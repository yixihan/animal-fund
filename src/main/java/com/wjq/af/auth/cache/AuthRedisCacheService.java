package com.wjq.af.auth.cache;

import cn.hutool.json.JSONUtil;
import com.wjq.af.dto.response.auth.AuthDtoResult;
import com.wjq.af.enums.RoleEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Token Redis 缓存管理 服务类
 *
 * @author yixihan
 * @date 2023/2/16 13:53
 */
@Slf4j
@Service
public class AuthRedisCacheService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    private static final String AUTH_KEY = "authCache";
    
    private static final String RESOURCE_KEY = "resourceCache";
    
    /**
     * 存放用户登录信息
     *
     * @param token jwtToken
     * @param auth 登录信息
     */
    public void put (String token, AuthDtoResult auth) {
        redisTemplate.opsForHash ().put (AUTH_KEY, token, JSONUtil.toJsonStr (auth));
    }
    
    /**
     * 取出用户登录信息
     *
     * @param token jwtToken
     * @return 登录信息
     */
    public AuthDtoResult get (String token) {
        Object jsonStr = redisTemplate.opsForHash ().get (AUTH_KEY, token);
    
        Assert.isNotNull (jsonStr, BizCodeEnum.TOKEN_ERR);
        
        return JSONUtil.toBean (jsonStr.toString (), AuthDtoResult.class);
    }
    
    /**
     * 删除用户登录信息
     *
     * @param token jwtToken
     */
    public void del (String token) {
        redisTemplate.opsForHash ().delete (AUTH_KEY, token);
    }
    
    public void putResource (String requestUri, List<RoleEnums> roleList) {
        redisTemplate.opsForHash ().put (RESOURCE_KEY, requestUri, JSONUtil.toJsonStr (roleList));
    }
    
    public List<Long> getResource (String requestUri) {
        Object jsonStr = redisTemplate.opsForHash ().get (RESOURCE_KEY, requestUri);
    
        Assert.isNotNull (jsonStr, BizCodeEnum.NULL_ERR);
        
        return JSONUtil.parseArray (jsonStr.toString ()).toList (RoleEnums.class).stream ()
                .map (RoleEnums::getRoleId).collect(Collectors.toList());
    }
    
    public void clearResource () {
        redisTemplate.delete (RESOURCE_KEY);
    }
}
