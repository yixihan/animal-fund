package com.wjq.af.controller.thirdpart;

import com.wjq.af.dto.request.thirdpart.CodeValidateDtoReq;
import com.wjq.af.service.thirdpart.PhotoCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "图形验证码 openApi")
@Validated
@RestController
@RequestMapping("/open/thirdpart/code")
public class PhotoCodeController {
    
    @Resource
    private PhotoCodeService service;
    
    @ApiOperation("生成图形验证码")
    @GetMapping("/create")
    void createCode(HttpServletResponse response, @RequestParam("uuid") @NotBlank String uuid) {
        service.createCode (response, uuid);
    }
    
    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    void validateCode (@RequestBody @Valid CodeValidateDtoReq req) {
        service.validateCode (req);
    }
}
