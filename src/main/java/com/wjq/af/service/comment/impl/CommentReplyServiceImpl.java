package com.wjq.af.service.comment.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.mapper.comment.CommentReplyMapper;
import com.wjq.af.pojo.comment.CommentReply;
import com.wjq.af.service.comment.CommentReplyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言板回复表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements CommentReplyService {

}
