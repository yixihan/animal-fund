package com.wjq.af.dto.response.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 留言回复明细-dtoResult
 *
 * @author yixihan
 * @date 2023/1/11 11:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("留言回复明细-dtoResult")
public class SonCommentDetailDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "回复留言板主键 ID")
    private Long rootId;
    
    @ApiModelProperty(value = "发送用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "留言信息")
    private String message;
    
    @ApiModelProperty(value = "回复时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
