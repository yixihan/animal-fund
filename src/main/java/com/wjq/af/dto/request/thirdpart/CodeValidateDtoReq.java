package com.wjq.af.dto.request.thirdpart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 图片验证码校验-dtoReq
 *
 * @author yixihan
 * @date 2023/2/17 14:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("图片验证码校验-dtoReq")
public class CodeValidateDtoReq {
    
    @ApiModelProperty(value = "uuid")
    @NotBlank
    private String uuid;
    
    @ApiModelProperty(value = "验证码")
    @NotBlank
    private String code;
}
