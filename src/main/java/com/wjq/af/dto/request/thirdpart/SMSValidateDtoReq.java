package com.wjq.af.dto.request.thirdpart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信验证码校验-dtoReq
 *
 * @author yixihan
 * @date 2023/2/17 15:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("短信验证码校验-dtoReq")
public class SMSValidateDtoReq {
    
    @ApiModelProperty(value = "手机号")
    private String mobile;
    
    @ApiModelProperty(value = "发送类型")
    private String mobileType;
    
    @ApiModelProperty(value = "验证码")
    private String code;
}

