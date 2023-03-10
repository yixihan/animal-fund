package com.wjq.af.controller.thirdpart;

import com.wjq.af.dto.request.thirdpart.EmailSendDtoReq;
import com.wjq.af.dto.request.thirdpart.EmailValidateDtoReq;
import com.wjq.af.service.thirdpart.EmailService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
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
@Slf4j
@Validated
@RestController
@RequestMapping("/open/thirdpart/email")
@Api(tags = "邮件 openApi")
public class EmailController {
    
    @Resource
    private EmailService service;
    
    @ApiOperation("发送邮件")
    @PostMapping(value = "/send/email", produces = "application/json")
    public void send(@RequestBody EmailSendDtoReq req) {
        Assert.isTrue (ValidationUtil.validateEmail (req.getEmail ()));
        service.send (req);
    }
    
    @ApiOperation("校验邮件验证码")
    @PostMapping(value = "/validate/email", produces = "application/json")
    public void validate(@RequestBody EmailValidateDtoReq req) {
        Assert.isTrue (ValidationUtil.validateEmail (req.getEmail ()));
        Assert.notNull (req.getCode ());
        service.validate (req);
    }
}
