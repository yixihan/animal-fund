package com.wjq.af.dto.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 添加留言-dtoReq
 *
 * @author yixihan
 * @date 2023/1/11 10:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加留言-dtoReq")
public class AddRootCommentDtoReq {
    
    @ApiModelProperty(value = "留言内容")
    @NotBlank(message = "留言内容不能为空")
    private String message;
}
