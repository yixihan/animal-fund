package com.wjq.af.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 志愿者注册-dtoReq
 *
 * @author yixihan
 * @date 2023/2/17 9:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("志愿者注册-dtoReq")
public class RegisterVolunteerDtoReq extends RegisterUserDtoReq {
    
    @ApiModelProperty(value = "用户身份证明")
    @NotBlank(message = "用户身份证明不能为空")
    private String userIdCardImg;
}
