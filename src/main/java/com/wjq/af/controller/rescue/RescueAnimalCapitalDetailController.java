package com.wjq.af.controller.rescue;


import com.wjq.af.auth.annotation.RoleAccess;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.dto.request.rescue.ModifyRescueAnimalCapitalDetailDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalCapitalDetailDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalCapitalDetailDtoResult;
import com.wjq.af.service.rescue.RescueAnimalCapitalDetailService;
import com.wjq.af.utils.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 救援动物资金明细表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Validated
@RoleAccess(value = {RoleEnums.ADMIN, RoleEnums.VOLUNTEER})
@RestController
@RequestMapping("/open/rescue/capital")
@Api(tags = "救援动物资金明细 OpenApi")
public class RescueAnimalCapitalDetailController {
    
    @Resource
    private RescueAnimalCapitalDetailService service;
    
    @ApiOperation("添加救援动物资金明细")
    @PostMapping(value = "/add", produces = "application/json")
    public void add(@RequestBody ModifyRescueAnimalCapitalDetailDtoReq req) {
        Assert.notNull (req.getRescueId ());
        service.add (req);
    }
    
    @ApiOperation("修改救援动物资金明细")
    @PostMapping(value = "/modify", produces = "application/json")
    public void modify(@RequestBody ModifyRescueAnimalCapitalDetailDtoReq req) {
        Assert.notNull (req.getId ());
        service.modify (req);
    }
    
    @ApiOperation("删除救援动物资金明细")
    @DeleteMapping(value = "/del", produces = "application/json")
    public void del(@RequestParam @NotNull Long id) {
        service.del (id);
    }
    
    @ApiOperation("查看救援动物资金明细")
    @RoleAccess
    @GetMapping(value = "/get", produces = "application/json")
    public JsonResponse<RescueAnimalCapitalDetailDtoResult> get(@RequestParam @NotNull Long id) {
        return JsonResponse.ok (service.get (id));
    }
    
    @ApiOperation("搜索救援动物资金明细")
    @RoleAccess
    @PostMapping(value = "/query", produces = "application/json")
    public JsonResponse<PageDtoResult<RescueAnimalCapitalDetailDtoResult>> query(@RequestBody QueryRescueAnimalCapitalDetailDtoReq req) {
        return JsonResponse.ok (service.query (req));
    }
    
}
