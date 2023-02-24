package com.wjq.af.service.comment.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.request.PageDtoReq;
import com.wjq.af.dto.request.comment.AddRootCommentDtoReq;
import com.wjq.af.dto.request.comment.AddSonCommentDtoReq;
import com.wjq.af.dto.request.comment.ReportCommentDtoReq;
import com.wjq.af.dto.request.comment.SonCommentDetailDtoReq;
import com.wjq.af.dto.response.PageDtoResult;
import com.wjq.af.dto.response.comment.RootCommentDetailDtoResult;
import com.wjq.af.dto.response.comment.SonCommentDetailDtoResult;
import com.wjq.af.enums.CommentTypeEnums;
import com.wjq.af.enums.ExamineStatusEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.mapper.comment.CommentRootMapper;
import com.wjq.af.pojo.comment.CommentReply;
import com.wjq.af.pojo.comment.CommentReport;
import com.wjq.af.pojo.comment.CommentRoot;
import com.wjq.af.service.comment.CommentReplyService;
import com.wjq.af.service.comment.CommentReportService;
import com.wjq.af.service.comment.CommentRootService;
import com.wjq.af.utils.Assert;
import com.wjq.af.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 留言板主表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Slf4j
@Service
public class CommentRootServiceImpl extends ServiceImpl<CommentRootMapper, CommentRoot> implements CommentRootService {
    
    @Resource
    private CommentReplyService replyService;
    
    @Resource
    private CommentReportService reportService;
    
    @Resource
    private TokenService tokenService;
    
    @Override
    public void addRootComment(AddRootCommentDtoReq req) {
        CommentRoot commentRoot = BeanUtil.toBean (req, CommentRoot.class);
        commentRoot.setUserId (tokenService.getCacheUserId ());
    
        Assert.isTrue (save (commentRoot), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void addSonComment(AddSonCommentDtoReq req) {
        // 校验留言是否存在
        int count = lambdaQuery ()
                .eq (CommentRoot::getId, req.getRootId ())
                .count ();
        
        Assert.isTrue (count == 1, new BizException ("留言不存在!"));
        
        CommentReply commentReply = BeanUtil.toBean (req, CommentReply.class);
        commentReply.setUserId (tokenService.getCacheUserId ());
    
        Assert.isTrue (replyService.save (commentReply), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void delRootComment(Long rootCommentId) {
        // 删除留言
        Assert.isTrue (removeById (rootCommentId), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
        // 删除留言回复
        List<Long> replyIdList = replyService.lambdaQuery ()
                .select (CommentReply::getId)
                .eq (CommentReply::getRootId, rootCommentId)
                .list ()
                .stream ()
                .map (CommentReply::getId)
                .collect(Collectors.toList());
        
        Assert.isTrue (replyService.removeByIds (replyIdList));
    }
    
    @Override
    public void delSonComment(Long sunCommentId) {
        Assert.isTrue (replyService.removeById (sunCommentId), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public Integer commentCount() {
        return count () + replyService.count ();
    }
    
    @Override
    public PageDtoResult<RootCommentDetailDtoResult> rootCommentDetail(PageDtoReq req) {
        // 分页查询留言板
        Page<CommentRoot> rootPage = lambdaQuery ()
                .orderByDesc (CommentRoot::getCreateTime)
                .page (PageUtil.toPage (req));
    
        // 转换为 RootCommentDetailDtoResult 格式
        PageDtoResult<RootCommentDetailDtoResult> pageDtoResult = PageUtil.pageToPageDtoResult (
                rootPage,
                (o) -> BeanUtil.toBean (o, RootCommentDetailDtoResult.class)
        );
    
        // 如果父评论不为空
        if (CollectionUtil.isNotEmpty (pageDtoResult.getRecords ())) {
            // 获取子评论明细
            Map<Long, List<SonCommentDetailDtoResult>> sonCommentDetail = sonCommentDetail (
                    pageDtoResult.getRecords ().stream ()
                            .map (RootCommentDetailDtoResult::getId)
                            .collect (Collectors.toList ())
            );
        
            // 设置父评论的子评论
            pageDtoResult.getRecords ()
                    .forEach ((o) -> o.setSonCommentDetailList (sonCommentDetail.get (o.getId ())));
        }
        return pageDtoResult;
    }
    
    @Override
    public PageDtoResult<SonCommentDetailDtoResult> sonCommentDetail(SonCommentDetailDtoReq req) {
        // 分页查询留言板
        Page<CommentReply> rootPage = replyService.lambdaQuery ()
                .eq (CommentReply::getRootId, req.getRootId ())
                .orderByDesc (CommentReply::getCreateTime)
                .page (PageUtil.toPage (req));
    
        // 转换为 RootCommentDetailDtoResult 格式
        return PageUtil.pageToPageDtoResult (
                rootPage,
                (o) -> BeanUtil.toBean (o, SonCommentDetailDtoResult.class)
        );
    }
    
    @Override
    public void reportComment(ReportCommentDtoReq req) {
        if (CommentTypeEnums.ROOT.getType ().equals (req.getMessageType ())) {
            CommentRoot commentRoot = getById (req.getMessageId ());
            
            Assert.notNull (commentRoot, new BizException ("该留言不存在"));
        } else if (CommentTypeEnums.REPLY.getType ().equals (req.getMessageType ())) {
            CommentReply commentReply = replyService.getById (req.getMessageId ());
    
            Assert.notNull (commentReply, new BizException ("该留言不存在"));
        }
        CommentReport commentReport = BeanUtil.toBean (req, CommentReport.class);
        commentReport.setReportStatus (ExamineStatusEnums.UN_EXAMINE.name ());
        commentReport.setUserId (tokenService.getCacheUserId ());
        Assert.isTrue (reportService.save (commentReport), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    /**
     * 获取父评论的所有子评论, 以列表形式返回
     *
     * @param rootCommentIdList 父评论 ID
     * @return Map 集合, rootCommentId => key, 子评论列表 => value
     */
    private Map<Long, List<SonCommentDetailDtoResult>> sonCommentDetail (List<Long> rootCommentIdList) {
        if (CollectionUtil.isEmpty (rootCommentIdList)) {
            return new HashMap<> ();
        }
        return replyService.lambdaQuery ()
                .in (CommentReply::getRootId, rootCommentIdList)
                .orderByDesc (CommentReply::getCreateTime)
                .list ()
                .stream ()
                .map ((o) -> BeanUtil.toBean (o, SonCommentDetailDtoResult.class))
                .collect (Collectors.groupingBy (
                        SonCommentDetailDtoResult::getRootId,
                        HashMap::new,
                        Collectors.toList ()));
    }
}
