package com.wjq.af.service.examine.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.PageDtoReq;
import com.wjq.af.dto.request.examine.ExamineDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.comment.CommentReportDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalCapitalDetailDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalInfoDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalStatusDtoResult;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.pojo.comment.CommentReport;
import com.wjq.af.pojo.rescue.RescueAnimalCapitalDetail;
import com.wjq.af.pojo.rescue.RescueAnimalInfo;
import com.wjq.af.pojo.rescue.RescueAnimalStatus;
import com.wjq.af.pojo.user.User;
import com.wjq.af.service.comment.CommentReportService;
import com.wjq.af.service.examine.ExamineService;
import com.wjq.af.service.rescue.RescueAnimalCapitalDetailService;
import com.wjq.af.service.rescue.RescueAnimalInfoService;
import com.wjq.af.service.rescue.RescueAnimalStatusService;
import com.wjq.af.service.user.UserService;
import com.wjq.af.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 审核 服务实现类
 *
 * @author yixihan
 * @date 2023/2/21 15:14
 */
@Slf4j
@Service
public class ExamineServiceImpl implements ExamineService {
    
    @Resource
    private TokenService tokenService;
    
    @Resource
    private UserService userService;
    
    @Resource
    private RescueAnimalInfoService rescueAnimalInfoService;
    
    @Resource
    private RescueAnimalStatusService rescueAnimalStatusService;
    
    @Resource
    private RescueAnimalCapitalDetailService rescueAnimalCapitalDetailService;
    
    @Resource
    private CommentReportService commentReportService;
    
    @Override
    public PageDtoResult<UserDtoResult> unExamineVolunteer(PageDtoReq req) {
        Page<User> page = userService.lambdaQuery ()
                .eq (User::getExamineStatus, ExamineStatusEnums.UN_EXAMINE)
                .orderByDesc (User::getUpdateTime)
                .page (PageUtils.toPage (req));
        
        return PageUtils.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, UserDtoResult.class)
        );
    }
    
    @Override
    public void examineVolunteer(ExamineDtoReq req) {
    
    }
    
    @Override
    public PageDtoResult<RescueAnimalInfoDtoResult> unExamineRescueAnimalInfo(PageDtoReq req) {
        Page<RescueAnimalInfo> page = rescueAnimalInfoService.lambdaQuery ()
                .eq (RescueAnimalInfo::getExamineStatus, ExamineStatusEnums.UN_EXAMINE)
                .orderByDesc (RescueAnimalInfo::getUpdateTime)
                .page (PageUtils.toPage (req));
    
        return PageUtils.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, RescueAnimalInfoDtoResult.class)
        );
    }
    
    @Override
    public void examineRescueAnimalInfo(ExamineDtoReq req) {
    
    }
    
    @Override
    public PageDtoResult<RescueAnimalStatusDtoResult> unExamineRescueAnimalStatus(PageDtoReq req) {
        Page<RescueAnimalStatus> page = rescueAnimalStatusService.lambdaQuery ()
                .eq (RescueAnimalStatus::getExamineStatus, ExamineStatusEnums.UN_EXAMINE)
                .orderByDesc (RescueAnimalStatus::getUpdateTime)
                .page (PageUtils.toPage (req));
    
        return PageUtils.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, RescueAnimalStatusDtoResult.class)
        );
    }
    
    @Override
    public void examineRescueAnimalStatus(ExamineDtoReq req) {
    
    }
    
    @Override
    public PageDtoResult<RescueAnimalCapitalDetailDtoResult> unExamineRescueAnimalCapitalDetail(PageDtoReq req) {
        Page<RescueAnimalCapitalDetail> page = rescueAnimalCapitalDetailService.lambdaQuery ()
                .eq (RescueAnimalCapitalDetail::getExamineStatus, ExamineStatusEnums.UN_EXAMINE)
                .orderByDesc (RescueAnimalCapitalDetail::getUpdateTime)
                .page (PageUtils.toPage (req));
    
        return PageUtils.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, RescueAnimalCapitalDetailDtoResult.class)
        );
    }
    
    @Override
    public void examineRescueAnimalCapitalDetail(ExamineDtoReq req) {
    
    }
    
    @Override
    public PageDtoResult<CommentReportDtoResult> unExamineComment(PageDtoReq req) {
        Page<CommentReport> page = commentReportService.lambdaQuery ()
                .eq (CommentReport::getReportStatus, ExamineStatusEnums.UN_EXAMINE)
                .orderByDesc (CommentReport::getUpdateTime)
                .page (PageUtils.toPage (req));
    
        return PageUtils.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, CommentReportDtoResult.class)
        );
    }
    
    @Override
    public void examineComment(ExamineDtoReq req) {
    
    }
}
