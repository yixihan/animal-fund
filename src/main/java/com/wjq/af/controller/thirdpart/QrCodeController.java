package com.wjq.af.controller.thirdpart;

import com.wjq.af.service.thirdpart.QrCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 二维码 OpenApi
 *
 * @author yixihan
 * @date 2023/2/17 14:29
 */
@Api(tags = "二维码生成 OpenApi")
@RestController
@RequestMapping("/open/thirdpart/qr")
public class QrCodeController {
    
    @Resource
    private QrCodeService service;
    
    @ApiOperation("生成二维码")
    @GetMapping(value = "/create", produces = "application/json")
    void create(HttpServletResponse response, @RequestParam("param") String param) {
        service.create (response, param);
    }
}
