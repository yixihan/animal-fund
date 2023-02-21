package com.wjq.af.service.rescue;

import com.wjq.af.dto.request.rescue.ModifyRescueAnimalCapitalDetailDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalCapitalDetailDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalCapitalDetailDtoResult;
import com.wjq.af.pojo.rescue.RescueAnimalCapitalDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 救援动物资金明细表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
public interface RescueAnimalCapitalDetailService extends IService<RescueAnimalCapitalDetail> {
    
    /**
     * 添加救援动物资金明细
     *
     * @param req 请求参数
     */
    void add(ModifyRescueAnimalCapitalDetailDtoReq req);
    
    /**
     * 修改救援动物资金明细
     *
     * @param req 请求参数
     */
    void modify(ModifyRescueAnimalCapitalDetailDtoReq req);
    
    /**
     * 删除救援动物资金明细
     *
     * @param id 主键 ID
     */
    void del(Long id);
    
    /**
     * 查看救援动物资金明细
     *
     * @param id 主键 ID
     */
    RescueAnimalCapitalDetailDtoResult get(Long id);
    
    /**
     * 搜索救援动物资金明细
     *
     * @param req 请求参数
     */
    PageDtoResult<RescueAnimalCapitalDetailDtoResult> query(QueryRescueAnimalCapitalDetailDtoReq req);
}
