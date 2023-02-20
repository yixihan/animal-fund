package com.wjq.af.dto.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 回复留言-dtoReq
 *
 * @author yixihan
 * @date 2023/1/11 10:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("回复留言-dtoReq")
public class AddSonCommentDtoReq {
    
    @ApiModelProperty(value = "父留言 id")
    @NotNull(message = "父留言 id 不能为空")
    private Long rootId;
    
    @ApiModelProperty(value = "回复内容")
    @NotBlank(message = "回复内容不能为空")
    private String message;
}
