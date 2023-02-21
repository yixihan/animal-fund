package com.wjq.af.service.rescue;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjq.af.dto.request.rescue.ApplyRescueAnimalDtoReq;
import com.wjq.af.dto.request.rescue.ModifyRescueAnimalStatusDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalStatusDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalStatusDtoResult;
import com.wjq.af.pojo.rescue.RescueAnimalStatus;

/**
 * <p>
 * 救援动物状态表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
public interface RescueAnimalStatusService extends IService<RescueAnimalStatus> {
    
    /**
     * 申请对动物进行救治
     *
     * @param req 请求参数
     */
    void apply(ApplyRescueAnimalDtoReq req);
    
    /**
     * 更新动物救援状态
     *
     * @param req 请求参数
     */
    void modify(ModifyRescueAnimalStatusDtoReq req);
    
    /**
     * 查看动物救援状态
     *
     * @param id 主键 id
     * @return {@link RescueAnimalStatusDtoResult}
     */
    RescueAnimalStatusDtoResult get(Long id);
    
    /**
     * 搜索动物救援状态
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<RescueAnimalStatusDtoResult>} {@link RescueAnimalStatusDtoResult}
     */
    PageDtoResult<RescueAnimalStatusDtoResult> query(QueryRescueAnimalStatusDtoReq req);
}
