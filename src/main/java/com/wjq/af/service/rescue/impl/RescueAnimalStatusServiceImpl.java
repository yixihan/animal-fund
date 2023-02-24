package com.wjq.af.service.rescue.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.rescue.ApplyRescueAnimalDtoReq;
import com.wjq.af.dto.request.rescue.ModifyRescueAnimalStatusDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalStatusDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalStatusDtoResult;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.enums.RescueStatusEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.mapper.rescue.RescueAnimalStatusMapper;
import com.wjq.af.pojo.rescue.RescueAnimalInfo;
import com.wjq.af.pojo.rescue.RescueAnimalStatus;
import com.wjq.af.service.rescue.RescueAnimalInfoService;
import com.wjq.af.service.rescue.RescueAnimalStatusService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 救援动物状态表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Service
public class RescueAnimalStatusServiceImpl extends ServiceImpl<RescueAnimalStatusMapper, RescueAnimalStatus> implements RescueAnimalStatusService {
    
    @Resource
    private RescueAnimalInfoService rescueAnimalInfoService;
    
    @Resource
    private TokenService tokenService;
    
    @Override
    public void apply(ApplyRescueAnimalDtoReq req) {
        // 校验该动物救援信息是否存在, 审批是否通过
        RescueAnimalInfo rescueAnimalInfo = rescueAnimalInfoService.lambdaQuery ()
                .eq (RescueAnimalInfo::getId, req.getRescueId ())
                .one ();
    
        Assert.notNull (rescueAnimalInfo, new BizException ("该救援信息不存在"));
        Assert.isTrue (ExamineStatusEnums.EXAMINE_SUCCESS.getValue ().equals (rescueAnimalInfo.getExamineStatus ()),
                new BizException ("该救援信息审核未通过"));
    
        RescueAnimalStatus rescueAnimalStatus = lambdaQuery ()
                .eq (RescueAnimalStatus::getRescueId, req.getRescueId ())
                .one ();
        
        // 校验该动物救援状态, 是否已被其他用户申请
        Assert.isNull (rescueAnimalStatus.getUserId (), new BizException ("该动物已被其他用户申请救援"));
        
        rescueAnimalStatus.setUserId (tokenService.getCacheUserId ());
        rescueAnimalStatus.setExamineStatus (ExamineStatusEnums.UN_EXAMINE.name ());
        rescueAnimalStatus.setRescueStatus (RescueStatusEnums.EXAMINING.getValue ());
        
        Assert.isTrue (updateById (rescueAnimalStatus), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void modify(ModifyRescueAnimalStatusDtoReq req) {
        RescueAnimalStatus rescueAnimalStatus = BeanUtil.toBean (req, RescueAnimalStatus.class);
        
        rescueAnimalStatus.setVersion (getById (req.getId ()).getVersion ());
        Assert.isTrue (updateById (rescueAnimalStatus), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public RescueAnimalStatusDtoResult get(Long id) {
        return BeanUtil.toBean (getById (id), RescueAnimalStatusDtoResult.class);
    }
    
    @Override
    public PageDtoResult<RescueAnimalStatusDtoResult> query(QueryRescueAnimalStatusDtoReq req) {
        Page<RescueAnimalStatus> page = lambdaQuery ()
                .eq (req.getUserId () != null, RescueAnimalStatus::getUserId, req.getUserId ())
                .eq (req.getRescueId () != null, RescueAnimalStatus::getRescueId, req.getRescueId ())
                .in (CollectionUtil.isNotEmpty (req.getRescueStatus ()),
                        RescueAnimalStatus::getExamineStatus,
                        req.getRescueStatus ())
                .orderByDesc (RescueAnimalStatus::getCreateTime)
                .page (PageUtil.toPage (req));
        
        return PageUtil.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, RescueAnimalStatusDtoResult.class)
        );
    }
}
