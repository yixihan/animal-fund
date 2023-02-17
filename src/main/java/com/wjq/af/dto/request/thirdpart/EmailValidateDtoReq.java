package com.wjq.af.dto.request.thirdpart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件验证码校验-dtoReq
 *
 * @author yixihan
 * @date 2023/2/17 15:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件验证码校验-dtoReq")
public class EmailValidateDtoReq {
    
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    @ApiModelProperty(value = "发送类型")
    private String emailType;
    
    @ApiModelProperty(value = "验证码")
    private String code;
}
