package com.wjq.af.service.thirdpart.impl;

import com.wjq.af.config.prop.CodeProp;
import com.wjq.af.exception.BizException;
import com.wjq.af.service.thirdpart.CodeService;
import com.wjq.af.utils.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 验证码 服务实现类
 *
 * @author yixihan
 * @date 2023/2/17 14:39
 */
@Slf4j
@Service
public class CodeServiceImpl implements CodeService {
    
    private static final char[] RANDOM_Arr = "1234567890".toCharArray ();
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private CodeProp codeProp;
    
    public String getCode(String keyName) {
        // 生成验证码
        String code = getRandomCode ();
        
        // 存入 redis
        addRedis (keyName, code);
        return code;
    }
    
    @Override
    public void validate(String keyName, String code) {
        Long expire = stringRedisTemplate.getExpire (keyName);
        
        // 校验验证码是否已经过期
        Assert.isFalse (expire == null || expire < 0L, new BizException ("验证码已过期!"));
        
        // 校验验证码是否正确
        Assert.isTrue (code.equals (stringRedisTemplate.opsForValue ().get (keyName)),
                new BizException ("验证码错误!"));
    }
    
    /**
     * 获取随机验证码, 并存入 redis 中
     *
     * @return code
     */
    private synchronized String getRandomCode() {
        int len = codeProp.getCodeLen ();
        Random random = new Random ();
        StringBuilder sb = new StringBuilder (len);
        for (int i = 0; i < len; i++) {
            sb.append (RANDOM_Arr[random.nextInt (RANDOM_Arr.length)]);
        }
        
        return sb.toString ();
    }
    
    @Override
    public void addRedis(String keyName, String code) {
        // 存入 redis
        stringRedisTemplate.opsForValue ().set (keyName, code);
        
        // 设置过期时间
        stringRedisTemplate.expire (keyName, codeProp.getTimeOut (), TimeUnit.MINUTES);
    }
}
