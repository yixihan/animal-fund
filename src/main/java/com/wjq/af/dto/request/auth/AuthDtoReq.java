package com.wjq.af.dto.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
}
