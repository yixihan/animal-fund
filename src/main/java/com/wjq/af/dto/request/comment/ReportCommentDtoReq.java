package com.wjq.af.dto.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 举报留言-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 16:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("举报留言-dtoReq")
public class ReportCommentDtoReq {
    
    @ApiModelProperty(value = "留言板主键 ID")
    private Long messageId;
    
    @ApiModelProperty(value = "举报留言类型[ROOT,REPLY]")
    private String messageType;
    
    @ApiModelProperty(value = "举报原因")
    private String reportReason;
}
