package com.wjq.af.controller.rescue;


import com.wjq.af.dto.request.rescue.ModifyRescueAnimalInfoDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalInfoDtoReq;
import com.wjq.af.dto.request.rescue.UserRescueAnimalInfoDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalInfoDtoResult;
import com.wjq.af.service.rescue.RescueAnimalInfoService;
import com.wjq.af.utils.Assert;
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
 * 救援动物信息 OpenApi
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/rescue/info")
@Api(tags = "救援动物信息 OpenApi")
public class RescueAnimalInfoController {
    
    @Resource
    private RescueAnimalInfoService service;
    
    @ApiOperation ("新增救援动物信息")
    @PostMapping(value = "/add", produces = "application/json")
    public void add (@RequestBody @Valid ModifyRescueAnimalInfoDtoReq req) {
        service.add (req);
    }
    
    @ApiOperation ("修改救援动物信息")
    @PostMapping(value = "/modify", produces = "application/json")
    public void modify (@RequestBody @Valid ModifyRescueAnimalInfoDtoReq req) {
        // 参数校验
        Assert.notNull (req.getId ());
        
        service.modify (req);
    }
    
    @ApiOperation ("删除救援动物信息")
    @DeleteMapping(value = "/del", produces = "application/json")
    public void del (@RequestParam @NotNull Long id) {
        service.del (id);
    }
    
    @ApiOperation ("查看救援动物信息")
    @GetMapping(value = "/get", produces = "application/json")
    public JsonResponse<RescueAnimalInfoDtoResult> get (@RequestParam @NotNull Long id) {
        return JsonResponse.ok (service.get (id));
    }
    
    @ApiOperation ("搜索救援动物信息")
    @PostMapping(value = "/query", produces = "application/json")
    public JsonResponse<PageDtoResult<RescueAnimalInfoDtoResult>> query (@RequestBody QueryRescueAnimalInfoDtoReq req) {
        return JsonResponse.ok (service.query (req));
    }
    
    @ApiOperation ("用户发布过的救援动物信息")
    @PostMapping(value = "/user", produces = "application/json")
    public JsonResponse<PageDtoResult<RescueAnimalInfoDtoResult>> userRecords (@RequestBody UserRescueAnimalInfoDtoReq req) {
        return JsonResponse.ok (service.userRecords (req));
    }
}
