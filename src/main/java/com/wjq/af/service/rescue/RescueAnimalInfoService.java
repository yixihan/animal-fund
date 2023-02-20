package com.wjq.af.service.rescue;

import com.wjq.af.dto.request.rescue.ModifyRescueAnimalInfoDtoReq;
import com.wjq.af.dto.request.rescue.QueryRescueAnimalInfoDtoReq;
import com.wjq.af.dto.request.rescue.UserRescueAnimalInfoDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalInfoDtoResult;
import com.wjq.af.pojo.rescue.RescueAnimalInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 救援动物信息表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
public interface RescueAnimalInfoService extends IService<RescueAnimalInfo> {
    
    /**
     * 新增救援动物信息
     *
     * @param req 请求参数
     */
    void add(ModifyRescueAnimalInfoDtoReq req);
    
    /**
     * 修改救援动物信息
     *
     * @param req 请求参数
     */
    void modify(ModifyRescueAnimalInfoDtoReq req);
    
    /**
     * 删除救援动物信息
     *
     * @param id 主键 id
     */
    void del(Long id);
    
    /**
     * 获取救援动物信息
     *
     * @param id 主键 id
     * @return {@link RescueAnimalInfoDtoResult}
     */
    RescueAnimalInfoDtoResult get(Long id);
    
    /**
     * 搜索救援动物信息
     *
     * @param req 请求参数
     * @return {code PageDtoResult<RescueAnimalInfoDtoResult>} {@link RescueAnimalInfoDtoResult}
     */
    PageDtoResult<RescueAnimalInfoDtoResult> query(QueryRescueAnimalInfoDtoReq req);
    
    /**
     * 用户发布过的救援动物信息
     *
     * @param req 请求参数
     * @return {code PageDtoResult<RescueAnimalInfoDtoResult>} {@link RescueAnimalInfoDtoResult}
     */
    PageDtoResult<RescueAnimalInfoDtoResult> userRecords(UserRescueAnimalInfoDtoReq req);
}
