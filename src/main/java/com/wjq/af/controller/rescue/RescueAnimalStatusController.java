package com.wjq.af.controller.rescue;


import com.wjq.af.auth.annotation.RoleAccess;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.dto.request.rescue.ApplyRescueAnimalDtoReq;
import com.wjq.af.dto.request.rescue.ModifyRescueAnimalStatusDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalStatusDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalStatusDtoResult;
import com.wjq.af.service.rescue.RescueAnimalStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 救援动物状态 OpenApi
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Validated
@RoleAccess(value = RoleEnums.VOLUNTEER)
@RestController
@RequestMapping("/open/rescue/status")
@Api(tags = "救援动物状态 OpenApi")
public class RescueAnimalStatusController {
    
    @Resource
    private RescueAnimalStatusService service;
    
    @ApiOperation("申请对动物进行救治")
    @RoleAccess(value = RoleEnums.VOLUNTEER)
    @PostMapping(value = "/apply", produces = "application/json")
    public void apply(@RequestBody @Valid ApplyRescueAnimalDtoReq req) {
        service.apply (req);
    }
    
    @ApiOperation("更新动物救援状态")
    @RoleAccess(value = {RoleEnums.VOLUNTEER, RoleEnums.ADMIN})
    @PostMapping(value = "/modify", produces = "application/json")
    public void modify(@RequestBody ModifyRescueAnimalStatusDtoReq req) {
        service.modify (req);
    }
    
    @ApiOperation("查看动物救援状态")
    @RoleAccess
    @GetMapping(value = "/get", produces = "application/json")
    public JsonResponse<RescueAnimalStatusDtoResult> get(@RequestParam @NotNull Long id) {
        return JsonResponse.ok (service.get (id));
    }
    
    @ApiOperation("搜索动物救援状态")
    @RoleAccess
    @PostMapping(value = "/query", produces = "application/json")
    public JsonResponse<PageDtoResult<RescueAnimalStatusDtoResult>> query(@RequestBody QueryRescueAnimalStatusDtoReq req) {
        return JsonResponse.ok (service.query (req));
    }
    
}
