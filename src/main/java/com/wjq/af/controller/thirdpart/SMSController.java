package com.wjq.af.controller.thirdpart;

import com.wjq.af.dto.request.thirdpart.SMSSendDtoReq;
import com.wjq.af.dto.request.thirdpart.SMSValidateDtoReq;
import com.wjq.af.service.thirdpart.SMSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短信 openApi
 *
 * @author yixihan
 * @date 2023/2/18 11:18
 */
@Api(tags = "短信 openApi")
@RestController
@RequestMapping("/open/thirdpart/sms")
public class SMSController {
    
    @Resource
    private SMSService service;
    
    @ApiOperation ("发送短信")
    @PostMapping("/send/mobile")
    void send (@RequestBody SMSSendDtoReq req) {
        service.send (req);
    }
    
    @ApiOperation ("校验验证码")
    @PostMapping("/validate/mobile")
    void validate (@RequestBody SMSValidateDtoReq req) {
        service.validate (req);
    }
}
