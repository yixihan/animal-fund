package com.wjq.af.service.comment.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.mapper.comment.CommentReportMapper;
import com.wjq.af.pojo.comment.CommentReport;
import com.wjq.af.service.comment.CommentReportService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言板举报表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Service
public class CommentReportServiceImpl extends ServiceImpl<CommentReportMapper, CommentReport> implements CommentReportService {

}
