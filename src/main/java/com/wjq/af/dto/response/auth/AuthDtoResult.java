package com.wjq.af.dto.response.auth;

import com.wjq.af.dto.response.user.RoleDtoResult;
import com.wjq.af.dto.response.user.UserDtoResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户认证-dtoResult
 *
 * @author yixihan
 * @date 2023/2/15 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户认证-dtoResult")
public class AuthDtoResult {
    
    @ApiModelProperty(value = "用户信息")
    private UserDtoResult user;
    
    @ApiModelProperty(value = "用户角色")
    private List<RoleDtoResult> roleList;
    
    @ApiModelProperty(value = "JwtToken")
    private String token;
    
}
