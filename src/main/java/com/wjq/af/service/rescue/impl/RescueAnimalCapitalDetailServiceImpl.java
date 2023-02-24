package com.wjq.af.service.rescue.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.rescue.ModifyRescueAnimalCapitalDetailDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalCapitalDetailDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalCapitalDetailDtoResult;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.pojo.rescue.RescueAnimalCapitalDetail;
import com.wjq.af.mapper.rescue.RescueAnimalCapitalDetailMapper;
import com.wjq.af.pojo.rescue.RescueAnimalStatus;
import com.wjq.af.service.rescue.RescueAnimalCapitalDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.service.rescue.RescueAnimalStatusService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 救援动物资金明细表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Service
public class RescueAnimalCapitalDetailServiceImpl extends ServiceImpl<RescueAnimalCapitalDetailMapper, RescueAnimalCapitalDetail> implements RescueAnimalCapitalDetailService {
    
    @Resource
    private TokenService tokenService;
    
    @Resource
    private RescueAnimalStatusService rescueAnimalStatusService;
    
    @Override
    public void add(ModifyRescueAnimalCapitalDetailDtoReq req) {
        RescueAnimalStatus rescueAnimalStatus = rescueAnimalStatusService.lambdaQuery ()
                .eq (RescueAnimalStatus::getRescueId, req.getRescueId ())
                .one ();
    
        Assert.notNull (rescueAnimalStatus, new BizException ("没有该动物救援记录"));
        Assert.isTrue (ExamineStatusEnums.EXAMINE_SUCCESS.getValue ().equals (rescueAnimalStatus.getRescueStatus ()),
                new BizException ("该动物救援信息尚未通过审核"));
        Assert.isTrue (rescueAnimalStatus.getUserId ().equals (tokenService.getCacheUserId ()),
                new BizException ("您不是该动物救援志愿者"));
    
        RescueAnimalCapitalDetail rescueAnimalCapitalDetail = BeanUtil.toBean (req, RescueAnimalCapitalDetail.class);
        rescueAnimalCapitalDetail.setExamineStatus (ExamineStatusEnums.UN_EXAMINE.name ());
        Assert.isTrue (save (rescueAnimalCapitalDetail), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void modify(ModifyRescueAnimalCapitalDetailDtoReq req) {
        RescueAnimalCapitalDetail rescueAnimalCapitalDetail = getById (req.getId ());
        
        Assert.notNull (rescueAnimalCapitalDetail, new BizException ("没有该条记录"));
        int version = rescueAnimalCapitalDetail.getVersion ();
        rescueAnimalCapitalDetail = BeanUtil.toBean (req, RescueAnimalCapitalDetail.class);
        rescueAnimalCapitalDetail.setVersion (version);
    
        Assert.isTrue (updateById (rescueAnimalCapitalDetail), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
    }
    
    @Override
    public void del(Long id) {
        Assert.isTrue (removeById (id), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public RescueAnimalCapitalDetailDtoResult get(Long id) {
        return BeanUtil.toBean (getById (id), RescueAnimalCapitalDetailDtoResult.class);
    }
    
    @Override
    public PageDtoResult<RescueAnimalCapitalDetailDtoResult> query(QueryRescueAnimalCapitalDetailDtoReq req) {
        Page<RescueAnimalCapitalDetail> page = lambdaQuery ()
                .eq (RescueAnimalCapitalDetail::getRescueId, req.getRescueId ())
                .orderByDesc (RescueAnimalCapitalDetail::getCreateTime)
                .page (PageUtil.toPage (req));
        
        return PageUtil.pageToPageDtoResult (
                page,
                (o) -> BeanUtil.toBean (o, RescueAnimalCapitalDetailDtoResult.class)
        );
    }
}
