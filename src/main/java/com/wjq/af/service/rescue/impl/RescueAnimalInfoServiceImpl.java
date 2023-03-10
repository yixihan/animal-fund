package com.wjq.af.service.rescue.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.rescue.ModifyRescueAnimalInfoDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalInfoDtoReq;
import com.wjq.af.dto.request.rescue.UserRescueAnimalInfoDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalInfoDtoResult;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.pojo.rescue.RescueAnimalInfo;
import com.wjq.af.mapper.rescue.RescueAnimalInfoMapper;
import com.wjq.af.service.rescue.RescueAnimalInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 救援动物信息表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Service
public class RescueAnimalInfoServiceImpl extends ServiceImpl<RescueAnimalInfoMapper, RescueAnimalInfo> implements RescueAnimalInfoService {
    
    @Resource
    private TokenService tokenService;
    
    @Override
    public void add(ModifyRescueAnimalInfoDtoReq req) {
        RescueAnimalInfo rescueAnimalInfo = BeanUtil.toBean (req, RescueAnimalInfo.class);
        rescueAnimalInfo.setUserId (tokenService.getCacheUserId ());
        rescueAnimalInfo.setExamineStatus (ExamineStatusEnums.UN_EXAMINE.getValue ());
    
        Assert.isTrue (save (rescueAnimalInfo), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void modify(ModifyRescueAnimalInfoDtoReq req) {
        RescueAnimalInfo rescueAnimalInfo = BeanUtil.toBean (req, RescueAnimalInfo.class);
        rescueAnimalInfo.setUserId (tokenService.getCacheUserId ());
        rescueAnimalInfo.setVersion (getById (req.getId ()).getVersion ());
    
    
        Assert.isTrue (updateById (rescueAnimalInfo), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void del(Long id) {
        Assert.isTrue (removeById (id), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public RescueAnimalInfoDtoResult get(Long id) {
        return BeanUtil.toBean (getById (id), RescueAnimalInfoDtoResult.class);
    }
    
    @Override
    public PageDtoResult<RescueAnimalInfoDtoResult> query(QueryRescueAnimalInfoDtoReq req) {
        Page<RescueAnimalInfo> pages = lambdaQuery ()
                .eq (StrUtil.isNotBlank (req.getAnimalType ()), RescueAnimalInfo::getAnimalType, req.getAnimalType ())
                .eq (StrUtil.isNotBlank (req.getInjuryDegree ()), RescueAnimalInfo::getInjuryDegree, req.getInjuryDegree ())
                .eq (RescueAnimalInfo::getExamineStatus, ExamineStatusEnums.EXAMINE_SUCCESS.getValue ())
                .orderByDesc (RescueAnimalInfo::getCreateTime)
                .page (PageUtil.toPage (req));
        
        return PageUtil.pageToPageDtoResult (
                pages,
                (o) -> BeanUtil.toBean (o, RescueAnimalInfoDtoResult.class)
        );
    }
    
    @Override
    public PageDtoResult<RescueAnimalInfoDtoResult> userRecords(UserRescueAnimalInfoDtoReq req) {
        if (req.getUserId () == null) {
            req.setUserId (tokenService.getCacheUserId ());
        }
        Page<RescueAnimalInfo> pages = lambdaQuery ()
                .eq (RescueAnimalInfo::getUserId, req.getUserId ())
                .orderByDesc (RescueAnimalInfo::getCreateTime)
                .page (PageUtil.toPage (req));
    
        return PageUtil.pageToPageDtoResult (
                pages,
                (o) -> BeanUtil.toBean (o, RescueAnimalInfoDtoResult.class)
        );
    }
}
