package com.wjq.af.service.examine.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjq.af.auth.enums.RoleEnums;
import com.wjq.af.dto.request.PageDtoReq;
import com.wjq.af.dto.request.examine.ExamineDtoReq;
import com.wjq.af.dto.request.thirdpart.ExamineEmailDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.comment.CommentReportDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalCapitalDetailDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalInfoDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalStatusDtoResult;
import com.wjq.af.dto.response.user.UserDtoResult;
import com.wjq.af.enums.CommentTypeEnums;
import com.wjq.af.enums.EmailTemplateEnums;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.enums.RescueStatusEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.pojo.comment.CommentReport;
import com.wjq.af.pojo.rescue.RescueAnimalCapitalDetail;
import com.wjq.af.pojo.rescue.RescueAnimalInfo;
import com.wjq.af.pojo.rescue.RescueAnimalStatus;
import com.wjq.af.pojo.user.User;
import com.wjq.af.pojo.user.UserRole;
import com.wjq.af.service.comment.CommentReplyService;
import com.wjq.af.service.comment.CommentReportService;
import com.wjq.af.service.comment.CommentRootService;
import com.wjq.af.service.examine.ExamineService;
import com.wjq.af.service.rescue.RescueAnimalCapitalDetailService;
import com.wjq.af.service.rescue.RescueAnimalInfoService;
import com.wjq.af.service.rescue.RescueAnimalStatusService;
import com.wjq.af.service.thirdpart.EmailService;
import com.wjq.af.service.user.UserRoleService;
import com.wjq.af.service.user.UserService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private UserService userService;
    
    @Resource
    private UserRoleService userRoleService;
    
    @Resource
    private RescueAnimalInfoService rescueAnimalInfoService;
    
    @Resource
    private RescueAnimalStatusService rescueAnimalStatusService;
    
    @Resource
    private RescueAnimalCapitalDetailService rescueAnimalCapitalDetailService;
    
    @Resource
    private CommentReportService commentReportService;
    
    @Resource
    private CommentRootService commentRootService;
    
    @Resource
    private CommentReplyService commentReplyService;
    
    @Resource
    private EmailService emailService;
    
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
    @Transactional(rollbackFor = BizException.class)
    public void examineVolunteer(ExamineDtoReq req) {
        User user = userService.getById (req.getId ());
    
        Assert.notNull (user, new BizException ("没有该志愿者信息"));
        Assert.isTrue (ExamineStatusEnums.UN_EXAMINE.getValue ().equals (user.getExamineStatus ()),
                new BizException ("该志愿者已审核"));
        
        if (req.getExamineStatus ()) {
            // 审核通过
            // 修改用户表
            user.setExamineStatus (ExamineStatusEnums.EXAMINE_SUCCESS.name ());
            Assert.isTrue (userService.updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
            
            // 赋予志愿者志愿者权限
            UserRole userRole = new UserRole ();
            userRole.setUserId (req.getId ());
            userRole.setRoleId (RoleEnums.VOLUNTEER.getRoleId ());
            Assert.isTrue (userRoleService.save (userRole), BizCodeEnum.FAILED_TYPE_BUSINESS);
            
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (user.getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_SUCCESS.name ());
            emailService.sendExamineResult (emailReq);
        } else {
            // 审核不通过
            // 修改用户表
            user.setExamineStatus (ExamineStatusEnums.EXAMINE_FAIL.name ());
            Assert.isTrue (userService.updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (user.getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_FAIL.name ());
            emailService.sendExamineResult (emailReq);
        }
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
        RescueAnimalInfo rescueAnimalInfo = rescueAnimalInfoService.getById (req.getId ());
    
        Assert.notNull (rescueAnimalInfo, new BizException ("没有该救援动物信息"));
        Assert.isTrue (ExamineStatusEnums.UN_EXAMINE.getValue ().equals (rescueAnimalInfo.getExamineStatus ()),
                new BizException ("该该救援动物已审核"));
    
        if (req.getExamineStatus ()) {
            // 审核通过
            // 修改救援动物信息表
            rescueAnimalInfo.setExamineStatus (ExamineStatusEnums.EXAMINE_SUCCESS.name ());
            Assert.isTrue (rescueAnimalInfoService.updateById (rescueAnimalInfo),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
        
            // 创建救援动物状态表
            RescueAnimalStatus rescueAnimalStatus = new RescueAnimalStatus ();
            rescueAnimalStatus.setRescueId (rescueAnimalInfo.getId ());
            rescueAnimalStatus.setRescueStatus (RescueStatusEnums.UN_DO.getValue ());
            rescueAnimalStatus.setAnimalBeforeRescueImg (rescueAnimalInfo.getApplyImg ());
            Assert.isTrue (rescueAnimalStatusService.save (rescueAnimalStatus),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
        
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (userService.getById (rescueAnimalInfo.getUserId ()).getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_SUCCESS.name ());
            emailService.sendExamineResult (emailReq);
        } else {
            // 审核不通过
            // 修改救援动物信息表
            rescueAnimalInfo.setExamineStatus (ExamineStatusEnums.EXAMINE_FAIL.name ());
            Assert.isTrue (rescueAnimalInfoService.updateById (rescueAnimalInfo),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
        
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (userService.getById (rescueAnimalInfo.getUserId ()).getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_FAIL.name ());
            emailService.sendExamineResult (emailReq);
        }
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
        RescueAnimalStatus rescueAnimalStatus = rescueAnimalStatusService.getById (req.getId ());
    
        Assert.notNull (rescueAnimalStatus, new BizException ("没有该救援动物申请信息"));
        Assert.isTrue (ExamineStatusEnums.UN_EXAMINE.getValue ().equals (rescueAnimalStatus.getExamineStatus ()),
                new BizException ("该该救援动物申请已审核"));
    
        if (req.getExamineStatus ()) {
            // 审核通过
            // 修改救援动物状态表
            rescueAnimalStatus.setExamineStatus (ExamineStatusEnums.EXAMINE_SUCCESS.name ());
            rescueAnimalStatus.setRescueStatus (RescueStatusEnums.FUNDRAISING.getValue ());
            Assert.isTrue (rescueAnimalStatusService.updateById (rescueAnimalStatus),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
            
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (userService.getById (rescueAnimalStatus.getUserId ()).getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_SUCCESS.name ());
            emailService.sendExamineResult (emailReq);
        } else {
            // 审核不通过
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (userService.getById (rescueAnimalStatus.getUserId ()).getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_FAIL.name ());
            emailService.sendExamineResult (emailReq);
            
            
            // 修改救援动物状态表
            rescueAnimalStatus.setExamineStatus (ExamineStatusEnums.UN_EXAMINE.name ());
            rescueAnimalStatus.setRescueStatus (RescueStatusEnums.UN_DO.getValue ());
            rescueAnimalStatus.setUserId (null);
            rescueAnimalStatus.setCapitalBudget (null);
            Assert.isTrue (rescueAnimalStatusService.updateById (rescueAnimalStatus),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
        }
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
        RescueAnimalCapitalDetail rescueAnimalCapitalDetail = rescueAnimalCapitalDetailService.getById (req.getId ());
    
        Assert.notNull (rescueAnimalCapitalDetail, new BizException ("没有该救援动物资金明细信息"));
        Assert.isTrue (ExamineStatusEnums.UN_EXAMINE.getValue ().equals (rescueAnimalCapitalDetail.getExamineStatus ()),
                new BizException ("该该救援动物资金明细已审核"));
    
        RescueAnimalStatus rescueAnimalStatus = rescueAnimalStatusService.lambdaQuery ()
                .eq (RescueAnimalStatus::getRescueId, rescueAnimalCapitalDetail.getRescueId ())
                .one ();
    
        if (req.getExamineStatus ()) {
            // 审核通过
            // 修改救援动物资金明细表
            rescueAnimalCapitalDetail.setExamineStatus (ExamineStatusEnums.EXAMINE_SUCCESS.name ());
            Assert.isTrue (rescueAnimalCapitalDetailService.updateById (rescueAnimalCapitalDetail),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
        
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (userService.getById (rescueAnimalStatus.getUserId ()).getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_SUCCESS.name ());
            emailService.sendExamineResult (emailReq);
        } else {
            // 审核不通过
            // 修改救援动物状态表
            rescueAnimalCapitalDetail.setExamineStatus (ExamineStatusEnums.UN_EXAMINE.name ());
            Assert.isTrue (rescueAnimalCapitalDetailService.updateById (rescueAnimalCapitalDetail),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
            
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (userService.getById (rescueAnimalStatus.getUserId ()).getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_FAIL.name ());
            emailService.sendExamineResult (emailReq);
        }
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
        CommentReport commentReport = commentReportService.getById (req.getId ());
    
        Assert.notNull (commentReport, new BizException ("没有该举报信息"));
        Assert.isTrue (ExamineStatusEnums.UN_EXAMINE.getValue ().equals (commentReport.getReportStatus ()),
                new BizException ("该举报已审核"));
    
        if (req.getExamineStatus ()) {
            // 审核通过
            // 修改留言举报表
            commentReport.setReportStatus (ExamineStatusEnums.EXAMINE_SUCCESS.name ());
            Assert.isTrue (commentReportService.updateById (commentReport),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
        
            // 删除该留言
            if (CommentTypeEnums.ROOT.getType ().equals (commentReport.getMessageType ())) {
                Assert.isTrue (commentRootService.removeById (commentReport.getMessageId ()),
                        BizCodeEnum.FAILED_TYPE_BUSINESS);
            } else if (CommentTypeEnums.REPLY.getType ().equals (commentReport.getMessageType ())) {
                Assert.isTrue (commentReplyService.removeById (commentReport.getMessageId ()),
                        BizCodeEnum.FAILED_TYPE_BUSINESS);
            }
        
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (userService.getById (commentReport.getUserId ()).getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_SUCCESS.name ());
            emailService.sendExamineResult (emailReq);
        } else {
            // 审核不通过
            // 修改留言举报表
            commentReport.setReportStatus (ExamineStatusEnums.EXAMINE_FAIL.name ());
            Assert.isTrue (commentReportService.updateById (commentReport),
                    BizCodeEnum.FAILED_TYPE_BUSINESS);
        
            // 发送邮件
            ExamineEmailDtoReq emailReq = new ExamineEmailDtoReq ();
            emailReq.setEmail (userService.getById (commentReport.getUserId ()).getUserEmail ());
            // TODO 待确认审核结果网址
            emailReq.setUrl ("1234");
            emailReq.setType (EmailTemplateEnums.EXAMINE_FAIL.name ());
            emailService.sendExamineResult (emailReq);
        }
    }
}
