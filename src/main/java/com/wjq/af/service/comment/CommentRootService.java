package com.wjq.af.service.comment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjq.af.dto.request.PageDtoReq;
import com.wjq.af.dto.request.comment.AddRootCommentDtoReq;
import com.wjq.af.dto.request.comment.AddSonCommentDtoReq;
import com.wjq.af.dto.request.comment.SonCommentDetailDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.comment.RootCommentDetailDtoResult;
import com.wjq.af.dto.response.comment.SonCommentDetailDtoResult;
import com.wjq.af.pojo.comment.CommentRoot;

/**
 * <p>
 * 留言板主表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
public interface CommentRootService extends IService<CommentRoot> {
    
    /**
     * 添加留言
     *
     * @param req 请求参数
     */
    void addRootComment(AddRootCommentDtoReq req);
    
    /**
     * 添加留言回复
     *
     * @param req 请求参数
     */
    void addSonComment(AddSonCommentDtoReq req);
    
    /**
     * 删除留言
     *
     * @param rootCommentId 留言 id
     */
    void delRootComment(Long rootCommentId);
    
    /**
     * 删除留言回复
     *
     * @param sunCommentId 留言回复 id
     */
    void delSonComment(Long sunCommentId);
    
    /**
     * 获取留言数量
     *
     * @return 留言数量
     */
    Integer commentCount();
    
    /**
     * 获取留言明细
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<RootCommentDetailDtoResult>} {@link RootCommentDetailDtoResult}
     */
    PageDtoResult<RootCommentDetailDtoResult> rootCommentDetail(PageDtoReq req);
    
    /**
     * 获取留言回复明细
     *
     * @param req 请求参数
     * @return {@code PageDtoResult<SonCommentDetailDtoResult>} {@link SonCommentDetailDtoResult}
     */
    PageDtoResult<SonCommentDetailDtoResult> sonCommentDetail(SonCommentDetailDtoReq req);
}
