package com.wjq.af.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 用户注册-dtoReq
 *
 * @author yixihan
 * @date 2023/2/17 9:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户注册-dtoReq")
public class RegisterUserDtoReq {
    
    @ApiModelProperty(value = "用户手机号")
    @NotBlank(message = "手机号不能为空")
    private String userMobile;
    
    @ApiModelProperty(value = "用户邮箱")
    @NotBlank(message = "邮件不能为空")
    private String userEmail;
    
    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "密码不能为空")
    private String userPassword;
    
    @ApiModelProperty(value = "用户姓名")
    @NotBlank(message = "用户姓名不能为空")
    private String userFullName;
    
    @ApiModelProperty(value = "用户昵称")
    @NotBlank(message = "用户昵称不能为空")
    private String userNickName;
    
    @ApiModelProperty(value = "用户头像")
    @NotBlank(message = "用户头像不能为空")
    private String userAvatar;
    
    @ApiModelProperty(value = "用户性别[男:MAN,女:WOMAN")
    @NotBlank(message = "用户性别不能为空")
    private String userGender;
    
    @ApiModelProperty(value = "用户年龄 [0-150]")
    @NotNull(message = "用户年龄不能为空")
    @Min (value = 0, message = "用户年龄不能小于0")
    @Max(value = 150, message = "用户年龄不能大于150")
    private Integer userAge;
    
    @ApiModelProperty(value = "用户生日[yyyy-MM-dd]")
    @Past(message = "用户生日选择错误")
    private Date userBirth;
    
    @ApiModelProperty(value = "用户身份证")
    @NotBlank(message = "身份证号不能为空")
    private String userIdCard;
    
    @ApiModelProperty(value = "用户个性签名")
    private String userSign;
}
