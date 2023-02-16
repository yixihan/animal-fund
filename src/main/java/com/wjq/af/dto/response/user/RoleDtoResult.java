package com.wjq.af.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色-dtoResult
 *
 * @author yixihan
 * @date 2023/2/15 17:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("角色-dtoResult")
public class RoleDtoResult {
    
    @ApiModelProperty(value = "角色 ID")
    private Long id;
    
    @ApiModelProperty(value = "角色名")
    private String roleName;
}
