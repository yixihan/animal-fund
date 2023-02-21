package com.wjq.af.service.rescue.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.rescue.AnimalContributionRecordDtoReq;
import com.wjq.af.dto.request.rescue.ContributionDtoReq;
import com.wjq.af.dto.request.rescue.UserContributionRecordDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.ContributionDtoResult;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.pojo.rescue.RescueAnimalContribution;
import com.wjq.af.mapper.rescue.RescueAnimalContributionMapper;
import com.wjq.af.pojo.rescue.RescueAnimalInfo;
import com.wjq.af.service.rescue.RescueAnimalContributionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.service.rescue.RescueAnimalInfoService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 救援动物捐款信息表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Service
public class RescueAnimalContributionServiceImpl extends ServiceImpl<RescueAnimalContributionMapper, RescueAnimalContribution> implements RescueAnimalContributionService {
    
    @Resource
    private RescueAnimalInfoService rescueAnimalInfoService;
    
    @Resource
    private TokenService tokenService;
    
    @Override
    public void contribution(ContributionDtoReq req) {
        // 校验该救援动物是否存在
        RescueAnimalInfo info = rescueAnimalInfoService.lambdaQuery ()
                .eq (RescueAnimalInfo::getId, req.getRescueId ())
                .eq (RescueAnimalInfo::getExamineStatus, ExamineStatusEnums.EXAMINE_SUCCESS)
                .one ();
    
        Assert.notNull (info, new BizException ("该救援动物不存在或审批未成功"));
        
        // 捐款
        RescueAnimalContribution rescueAnimalContribution = BeanUtil.toBean (req, RescueAnimalContribution.class);
        rescueAnimalContribution.setUserId (tokenService.getCacheUserId ());
        
        Assert.isTrue (save (rescueAnimalContribution), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public PageDtoResult<ContributionDtoResult> userRecord(UserContributionRecordDtoReq req) {
        // 默认展示登录用户的捐款记录
        if (req.getUserId () == null) {
            req.setUserId (tokenService.getCacheUserId ());
        }
    
        Page<RescueAnimalContribution> page = lambdaQuery ()
                .eq (RescueAnimalContribution::getUserId, req.getUserId ())
                .orderByDesc (RescueAnimalContribution::getCreateTime)
                .page (PageUtils.toPage (req));
        
        return PageUtils.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, ContributionDtoResult.class)
        );
    }
    
    @Override
    public PageDtoResult<ContributionDtoResult> animalRecord(AnimalContributionRecordDtoReq req) {
        Page<RescueAnimalContribution> page = lambdaQuery ()
                .eq (RescueAnimalContribution::getRescueId, req.getRescueId ())
                .orderByDesc (RescueAnimalContribution::getCreateTime)
                .page (PageUtils.toPage (req));
    
        return PageUtils.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, ContributionDtoResult.class)
        );
    }
}
