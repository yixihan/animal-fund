package com.wjq.af.dto.request.thirdpart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 实名认证-dtoReq
 *
 * @author yixihan
 * @date 2023/2/20 13:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("实名认证-dtoReq")
public class RealNameAuthDtoReq {
    
    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message = "姓名不能为空")
    private String realName;
    
    @ApiModelProperty(value = "身份证号")
    @NotBlank(message = "身份证号不能为空")
    private String idCard;
}
