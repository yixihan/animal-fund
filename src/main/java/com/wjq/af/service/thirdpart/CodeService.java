package com.wjq.af.service.thirdpart;

/**
 * 验证码 服务类
 *
 * @author yixihan
 * @date 2023/2/17 14:39
 */
public interface CodeService {
    
    /**
     * 验证码生成
     *
     * @param keyName redis 缓存key
     * @return 验证码
     */
    String getCode (String keyName);
    
    /**
     * 验证码校验
     *
     * @param keyName redis 缓存 key
     * @param code 验证码
     */
    void validate (String keyName, String code);
    
    /**
     * 将验证码存入 redis 中， 并设置有效时间
     *
     * @param keyName redis key
     * @param code 验证码
     */
    void addRedis(String keyName, String code);
}
