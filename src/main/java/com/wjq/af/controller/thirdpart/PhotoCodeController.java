package com.wjq.af.controller.thirdpart;

import com.wjq.af.dto.request.thirdpart.CodeValidateDtoReq;
import com.wjq.af.service.thirdpart.PhotoCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 图形验证码 openApi
 *
 * @author yixihan
 * @date 2023/2/17 14:34
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/thirdpart/code")
@Api(tags = "图形验证码 openApi")
public class PhotoCodeController {
    
    @Resource
    private PhotoCodeService service;
    
    @ApiOperation("生成图形验证码")
    @GetMapping(value = "/create", produces = "application/json")
    public void create(HttpServletResponse response, @RequestParam("uuid") @NotBlank String uuid) {
        service.create (response, uuid);
    }
    
    @ApiOperation("校验验证码")
    @PostMapping("/validate")
    public void validateCode(@RequestBody @Valid CodeValidateDtoReq req) {
        service.validateCode (req);
    }
}
