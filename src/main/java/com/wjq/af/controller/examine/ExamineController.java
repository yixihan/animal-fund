package com.wjq.af.controller.examine;

import com.wjq.af.auth.annotation.RoleAccess;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.dto.request.PageDtoReq;
import com.wjq.af.dto.request.examine.ExamineDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.comment.CommentReportDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalCapitalDetailDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalInfoDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalStatusDtoResult;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.service.examine.ExamineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 审核 OpenApi
 * </p>
 *
 * @author yixihan
 * @date 2023/2/20 10:44
 */
@Slf4j
@Validated
@RoleAccess(value = RoleEnums.ADMIN)
@RestController
@RequestMapping("/open/report")
@Api(tags = "审核 OpenApi")
public class ExamineController {
    
    @Resource
    private ExamineService service;
    
    @ApiOperation ("查看待审核志愿者")
    @GetMapping(value = "/list/volunteer", produces = "application/json")
    public JsonResponse<PageDtoResult<UserDtoResult>> unExamineVolunteer (@RequestBody PageDtoReq req) {
        return JsonResponse.ok (service.unExamineVolunteer (req));
    }
    
    @ApiOperation ("审核志愿者")
    @PostMapping(value = "/examine/volunteer", produces = "application/json")
    public void examineVolunteer (@RequestBody @Valid ExamineDtoReq req) {
        service.examineVolunteer (req);
    }
    
    @ApiOperation ("查看待审核救援动物信息")
    @GetMapping(value = "/list/rescue/info", produces = "application/json")
    public JsonResponse<PageDtoResult<RescueAnimalInfoDtoResult>> unExamineRescueAnimalInfo (@RequestBody PageDtoReq req) {
        return JsonResponse.ok (service.unExamineRescueAnimalInfo (req));
    }
    
    @ApiOperation ("审核救援动物信息")
    @PostMapping(value = "/examine/rescue/info", produces = "application/json")
    public void examineRescueAnimalInfo (@RequestBody @Valid ExamineDtoReq req) {
        service.examineRescueAnimalInfo (req);
    }
    
    @ApiOperation ("查看待审核救援动物状态")
    @GetMapping(value = "/list/rescue/status", produces = "application/json")
    public JsonResponse<PageDtoResult<RescueAnimalStatusDtoResult>> unExamineRescueAnimalStatus (@RequestBody PageDtoReq req) {
        return JsonResponse.ok (service.unExamineRescueAnimalStatus (req));
    }
    
    @ApiOperation ("审核救援动物状态")
    @PostMapping(value = "/examine/rescue/status", produces = "application/json")
    public void examineRescueAnimalStatus (@RequestBody @Valid ExamineDtoReq req) {
        service.examineRescueAnimalStatus (req);
    }
    
    @ApiOperation ("查看待审核救援动物资金明细")
    @GetMapping(value = "/list/rescue/capital", produces = "application/json")
    public JsonResponse<PageDtoResult<RescueAnimalCapitalDetailDtoResult>> unExamineRescueAnimalCapitalDetail (@RequestBody PageDtoReq req) {
        return JsonResponse.ok (service.unExamineRescueAnimalCapitalDetail (req));
    }
    
    @ApiOperation ("审核救援动物资金明细")
    @PostMapping(value = "/examine/rescue/capital", produces = "application/json")
    public void examineRescueAnimalCapitalDetail (@RequestBody @Valid ExamineDtoReq req) {
        service.examineRescueAnimalCapitalDetail (req);
    }
    
    @ApiOperation ("查看待审核举报留言")
    @GetMapping(value = "/list/comment", produces = "application/json")
    public JsonResponse<PageDtoResult<CommentReportDtoResult>> unExamineComment (@RequestBody PageDtoReq req) {
        return JsonResponse.ok (service.unExamineComment (req));
    }
    
    @ApiOperation ("审核举报留言")
    @PostMapping(value = "/examine/comment", produces = "application/json")
    public void examineComment (@RequestBody @Valid ExamineDtoReq req) {
        service.examineComment (req);
    }
    
    
}
