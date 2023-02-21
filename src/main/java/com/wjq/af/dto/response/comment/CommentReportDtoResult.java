package com.wjq.af.dto.response.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 留言举报-dtoResult
 *
 * @author yixihan
 * @date 2023/2/21 15:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("留言举报-dtoResult")
public class CommentReportDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "留言板主键 ID")
    private Long messageId;
    
    @ApiModelProperty(value = "举报留言类型[ROOT,REPLY]")
    private String messageType;
    
    @ApiModelProperty(value = "举报用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "举报原因")
    private String reportReason;
    
    @ApiModelProperty(value = "举报状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String reportStatus;
    
    @ApiModelProperty(value = "举报时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
