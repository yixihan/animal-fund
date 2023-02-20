package com.wjq.af.controller.thirdpart;

import com.wjq.af.dto.request.thirdpart.EmailSendDtoReq;
import com.wjq.af.dto.request.thirdpart.EmailValidateDtoReq;
import com.wjq.af.service.thirdpart.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件 openApi
 *
 * @author yixihan
 * @date 2023/2/17 15:42
 */
@Api(tags = "邮件 openApi")
@RestController
@RequestMapping("/open/thirdpart/email")
public class EmailController {
    
    @Resource
    private EmailService service;
    
    @ApiOperation("发送邮件")
    @PostMapping(value = "/send/email", produces = "application/json")
    void send(@RequestBody EmailSendDtoReq req) {
        service.send (req);
    }
    
    @ApiOperation("校验邮件验证码")
    @PostMapping(value = "/validate/email", produces = "application/json")
    void validate(@RequestBody EmailValidateDtoReq req) {
        service.validate (req);
    }
}
