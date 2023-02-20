package com.wjq.af.controller.thirdpart;

import com.wjq.af.dto.request.thirdpart.OSSPolicyDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.service.thirdpart.OSSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 文件上传 openApi
 *
 * @author yixihan
 * @date 2023/2/18 11:12
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/thirdpart/oss")
@Api(tags = "文件上传 openApi")
public class OSSController {
    
    @Resource
    private OSSService service;
    
    @ApiOperation(value = "获取上传密钥")
    @PostMapping(value = "/upload/policy", produces = "application/json")
    JsonResponse<Map<String, String>> policy(@RequestBody OSSPolicyDtoReq req) {
        return JsonResponse.ok (service.policy (req));
    }
}

