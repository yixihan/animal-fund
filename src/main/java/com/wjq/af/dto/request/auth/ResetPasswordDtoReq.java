package com.wjq.af.dto.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 重置密码-dtoReq
 *
 * @author yixihan
 * @date 2023/2/17 11:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("重置密码-dtoReq")
public class ResetPasswordDtoReq {
    
    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;
    
    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;
    
    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
