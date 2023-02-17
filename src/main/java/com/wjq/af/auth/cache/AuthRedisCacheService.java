package com.wjq.af.auth.cache;

import cn.hutool.json.JSONUtil;
import com.wjq.af.auth.constant.AuthConstant;
import com.wjq.af.dto.response.auth.AuthDtoResult;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Redis 缓存管理 服务类
 *
 * @author yixihan
 * @date 2023/2/16 13:53
 */
@Slf4j
@Service
public class AuthRedisCacheService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 存放用户登录信息
     *
     * @param token jwtToken
     * @param auth 登录信息
     */
    public void put (String token, AuthDtoResult auth) {
        redisTemplate.opsForHash ().put (AuthConstant.AUTH_KEY, token, JSONUtil.toJsonStr (auth));
    }
    
    /**
     * 取出用户登录信息
     *
     * @param token jwtToken
     * @return 登录信息
     */
    public AuthDtoResult get (String token) {
        Object jsonStr = redisTemplate.opsForHash ().get (AuthConstant.AUTH_KEY, token);
    
        Assert.isNotNull (jsonStr, BizCodeEnum.TOKEN_ERR);
        
        return JSONUtil.toBean (jsonStr.toString (), AuthDtoResult.class);
    }
    
    /**
     * 删除用户登录信息
     *
     * @param token jwtToken
     */
    public void del (String token) {
        redisTemplate.opsForHash ().delete (AuthConstant.AUTH_KEY, token);
    }
    
    /**
     * 存储接口权限限制数据
     *
     * @param requestUri 接口请求路径
     * @param roleList 接口所需角色列表
     */
    public void putResource (String requestUri, List<RoleEnums> roleList) {
        redisTemplate.opsForHash ().put (AuthConstant.RESOURCE_KEY, requestUri, JSONUtil.toJsonStr (roleList));
    }
    
    /**
     * 获取接口所需角色列表
     *
     * @param requestUri 接口请求路径
     * @return {@code List<RoleId>}
     */
    public List<Long> getResource (String requestUri) {
        Object jsonStr = redisTemplate.opsForHash ().get (AuthConstant.RESOURCE_KEY, requestUri);
    
        Assert.isNotNull (jsonStr, BizCodeEnum.NULL_ERR);
        
        return JSONUtil.parseArray (jsonStr.toString ()).toList (RoleEnums.class).stream ()
                .map (RoleEnums::getRoleId).collect(Collectors.toList());
    }
    
    /**
     * 清除接口权限限制数据
     */
    public void clearResource () {
        redisTemplate.delete (AuthConstant.RESOURCE_KEY);
    }
}
