package com.wjq.af.controller.rescue;


import com.wjq.af.dto.request.rescue.AnimalContributionRecordDtoReq;
import com.wjq.af.dto.request.rescue.ContributionDtoReq;
import com.wjq.af.dto.request.rescue.UserContributionRecordDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.ContributionDtoResult;
import com.wjq.af.service.rescue.RescueAnimalContributionService;
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
 * 救援动物捐款信息 OpenApi
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/rescue/contribution")
@Api(tags = "救援动物捐款信息 OpenApi")
public class RescueAnimalContributionController {
    
    @Resource
    private RescueAnimalContributionService service;
    
    @ApiOperation("捐款")
    @PostMapping(value = "/contribution", produces = "application/json")
    public void contribution(@RequestBody ContributionDtoReq req) {
        service.contribution (req);
    }
    
    @ApiOperation("查看用户的捐款记录")
    @PostMapping(value = "/user/record", produces = "application/json")
    public JsonResponse<PageDtoResult<ContributionDtoResult>> userRecord(@RequestBody @Valid UserContributionRecordDtoReq req) {
        return JsonResponse.ok (service.userRecord (req));
    }
    
    @ApiOperation("查看救援动物的捐款记录")
    @PostMapping(value = "/animal/record", produces = "application/json")
    public JsonResponse<PageDtoResult<ContributionDtoResult>> animalRecord(@RequestBody @Valid AnimalContributionRecordDtoReq req) {
        return JsonResponse.ok (service.animalRecord (req));
    }
    
}
