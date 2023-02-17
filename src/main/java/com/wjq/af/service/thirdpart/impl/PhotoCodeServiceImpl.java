package com.wjq.af.service.thirdpart.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.wjq.af.config.prop.CodeProp;
import com.wjq.af.dto.request.thirdpart.CodeValidateDtoReq;
import com.wjq.af.service.thirdpart.CodeService;
import com.wjq.af.service.thirdpart.PhotoCodeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 图片验证码 服务实现类
 *
 * @author yixihan
 * @date 2023/2/17 14:36
 */
@Slf4j
@Service
public class PhotoCodeServiceImpl implements PhotoCodeService {
    
    @Resource
    private CodeService codeService;
    
    @Resource
    private CodeProp codeProp;
    
    @Override
    @SneakyThrows
    public void createCode(HttpServletResponse response, String uuid) {
        // 生成图片验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha (200, 100, 5, 20);
        
        // 获取验证码
        String code = captcha.getCode ();
    
        // 获取 redis key
        String keyName = String.format (codeProp.getCommonKey (), uuid);
        
        // 存入 redis
        codeService.addRedis (keyName, code);
        
        //图形验证码写出到响应体
        captcha.write (response.getOutputStream ());
    }
    
    @Override
    public void validateCode(CodeValidateDtoReq req) {
        // 获取 redis key
        String keyName = String.format (codeProp.getCommonKey (), req.getUuid ());
        
        // 验证码校验
        codeService.validate (keyName, req.getCode ());
    }
}
