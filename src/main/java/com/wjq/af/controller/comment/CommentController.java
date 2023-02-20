package com.wjq.af.controller.comment;


import com.wjq.af.dto.request.PageDtoReq;
import com.wjq.af.dto.request.comment.AddRootCommentDtoReq;
import com.wjq.af.dto.request.comment.AddSonCommentDtoReq;
import com.wjq.af.dto.request.comment.SonCommentDetailDtoReq;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.comment.RootCommentDetailDtoResult;
import com.wjq.af.dto.response.comment.SonCommentDetailDtoResult;
import com.wjq.af.service.comment.CommentRootService;
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
 * 留言板 OpenApi
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/open/comment")
@Api(tags = "留言板 OpenApi")
public class CommentController {
    
    @Resource
    private CommentRootService service;
    
    @ApiOperation("添加留言")
    @PostMapping(value = "/add/root", produces = "application/json")
    public void addRootComment(@RequestBody @Valid AddRootCommentDtoReq req) {
        service.addRootComment (req);
    }
    
    @ApiOperation("添加留言回复")
    @PostMapping(value = "/add/son", produces = "application/json")
    public void addSonComment(@RequestBody @Valid AddSonCommentDtoReq req) {
        service.addSonComment (req);
    }
    
    @ApiOperation("删除留言")
    @DeleteMapping(value = "/del/root", produces = "application/json")
    public void delRootComment(@RequestParam @NotNull Long rootCommentId) {
        service.delRootComment (rootCommentId);
    }
    
    @ApiOperation("删除留言回复")
    @DeleteMapping(value = "/del/son", produces = "application/json")
    public void delSonComment(@RequestParam @NotNull Long sunCommentId) {
        service.delSonComment (sunCommentId);
    }
    
    @ApiOperation("获取留言数量")
    @GetMapping(value = "/count", produces = "application/json")
    public JsonResponse<Integer> commentCount() {
        return JsonResponse.ok (service.commentCount ());
    }
    
    @ApiOperation("获取所有留言")
    @PostMapping(value = "/all", produces = "application/json")
    public JsonResponse<PageDtoResult<RootCommentDetailDtoResult>> rootCommentDetail(@RequestBody PageDtoReq req) {
        return JsonResponse.ok (service.rootCommentDetail (req));
    }
    
    @ApiOperation("获取留言所有回复")
    @PostMapping(value = "/all/son", produces = "application/json")
    public JsonResponse<PageDtoResult<SonCommentDetailDtoResult>> sonCommentDetail(@RequestBody SonCommentDetailDtoReq req) {
        return JsonResponse.ok (service.sonCommentDetail (req));
    }
    
}
