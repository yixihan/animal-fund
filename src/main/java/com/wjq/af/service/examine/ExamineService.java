package com.wjq.af.service.examine;

import com.wjq.af.dto.request.PageDtoReq;
import com.wjq.af.dto.request.examine.ExamineDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.comment.CommentReportDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalCapitalDetailDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalInfoDtoResult;
import com.wjq.af.dto.response.rescue.RescueAnimalStatusDtoResult;
import com.wjq.af.dto.response.user.UserDtoResult;

/**
 * 审核 服务类
 *
 * @author yixihan
 * @date 2023/2/21 15:13
 */
public interface ExamineService {
    
    /**
     * 查看待审核志愿者
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<UserDtoResult>} {@link UserDtoResult}
     */
    PageDtoResult<UserDtoResult> unExamineVolunteer(PageDtoReq req);
    
    /**
     * 审核志愿者
     *
     * @param req 请求参数
     */
    void examineVolunteer(ExamineDtoReq req);
    
    /**
     * 查看待审核救援动物信息
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<RescueAnimalInfoDtoResult>} {@link RescueAnimalInfoDtoResult}
     */
    PageDtoResult<RescueAnimalInfoDtoResult> unExamineRescueAnimalInfo(PageDtoReq req);
    
    /**
     * 审核救援动物信息
     *
     * @param req 请求参数
     */
    void examineRescueAnimalInfo(ExamineDtoReq req);
    
    /**
     * 查看待审核救援动物状态
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<RescueAnimalStatusDtoResult>} {@link RescueAnimalStatusDtoResult}
     */
    PageDtoResult<RescueAnimalStatusDtoResult> unExamineRescueAnimalStatus(PageDtoReq req);
    
    /**
     * 审核救援动物状态
     *
     * @param req 请求参数
     */
    void examineRescueAnimalStatus(ExamineDtoReq req);
    
    /**
     * 查看待审核救援动物资金明细
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<RescueAnimalCapitalDetailDtoResult>} {@link RescueAnimalCapitalDetailDtoResult}
     */
    PageDtoResult<RescueAnimalCapitalDetailDtoResult> unExamineRescueAnimalCapitalDetail(PageDtoReq req);
    
    /**
     * 审核救援动物资金明细
     *
     * @param req 请求参数
     */
    void examineRescueAnimalCapitalDetail(ExamineDtoReq req);
    
    /**
     * 查看待审核举报留言
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<CommentReportDtoResult>} {@link CommentReportDtoResult}
     */
    PageDtoResult<CommentReportDtoResult> unExamineComment(PageDtoReq req);
    
    /**
     * 审核举报留言
     *
     * @param req 请求参数
     */
    void examineComment(ExamineDtoReq req);
}
