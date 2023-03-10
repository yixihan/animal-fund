package com.wjq.af.dto.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 用户认证-dtoReq
 *
 * @author yixihan
 * @date 2023/2/15 17:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户认证-dtoReq")
public class AuthDtoReq {
    
    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    
    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
