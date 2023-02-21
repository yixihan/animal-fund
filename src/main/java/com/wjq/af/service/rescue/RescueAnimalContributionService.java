package com.wjq.af.service.rescue;

import com.wjq.af.dto.request.rescue.AnimalContributionRecordDtoReq;
import com.wjq.af.dto.request.rescue.ContributionDtoReq;
import com.wjq.af.dto.request.rescue.UserContributionRecordDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.ContributionDtoResult;
import com.wjq.af.pojo.rescue.RescueAnimalContribution;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 救援动物捐款信息表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
public interface RescueAnimalContributionService extends IService<RescueAnimalContribution> {
    
    /**
     * 捐款
     *
     * @param req 请求参数
     */
    void contribution(ContributionDtoReq req);
    
    /**
     * 查看用户的捐款记录
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<ContributionDtoResult>} {@link ContributionDtoResult}
     */
    PageDtoResult<ContributionDtoResult> userRecord(UserContributionRecordDtoReq req);
    
    /**
     * 查看救援动物的捐款记录
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<ContributionDtoResult>} {@link ContributionDtoResult}
     */
    PageDtoResult<ContributionDtoResult> animalRecord(AnimalContributionRecordDtoReq req);
}
