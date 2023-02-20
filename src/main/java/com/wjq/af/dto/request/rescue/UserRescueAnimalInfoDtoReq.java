package com.wjq.af.dto.request.rescue;

import com.wjq.af.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索救援动物信息-dtoReq
 *
 * @author yixihan
 * @date 2023/2/20 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索救援动物信息-dtoReq")
public class UserRescueAnimalInfoDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
}
