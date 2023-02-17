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
public class RegisterVolunteerDtoReq extends RegisterUserDtoReq{
    
    @ApiModelProperty(value = "用户身份证明")
    @NotBlank(message = "用户身份证明不能为空")
    private String userIdCardImg;
    
    @ApiModelProperty(value = "审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String examineStatus;
}
