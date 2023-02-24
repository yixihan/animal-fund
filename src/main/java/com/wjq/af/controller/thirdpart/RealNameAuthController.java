package com.wjq.af.controller.thirdpart;

import com.wjq.af.dto.request.thirdpart.RealNameAuthDtoReq;
import com.wjq.af.service.thirdpart.RealNameAuthService;
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
import javax.validation.Valid;

/**
 * <p>
 * 实名认证 OpenApi
 * </p>
 *
 * @author yixihan
 * @date 2023/2/20 13:34
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/thirdpart/auth")
@Api(tags = "实名认证 OpenApi")
public class RealNameAuthController {
    
    @Resource
    private RealNameAuthService service;
    
    @ApiOperation ("实名认证")
    @PostMapping(value = "/validate", produces = "application/json")
    public void auth (@RequestBody @Valid RealNameAuthDtoReq req) {
        // 参数校验
        Assert.isTrue (ValidationUtil.validateIdCard (req.getIdCard ()));
        
        service.auth (req);
    }
}
