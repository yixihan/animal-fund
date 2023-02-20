package com.wjq.af.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户信息修改-dtoReq
 *
 * @author yixihan
 * @date 2023/2/20 11:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户信息修改-dtoReq")
public class ModifyUserDtoReq {
    
    @ApiModelProperty(value = "用户手机号")
    private String userMobile;
    
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;
    
    @ApiModelProperty(value = "用户姓名")
    private String userFullName;
    
    @ApiModelProperty(value = "用户昵称")
    private String userNickName;
    
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;
    
    @ApiModelProperty(value = "用户性别[男:MAN,女:WOMAN")
    private String userGender;
    
    @ApiModelProperty(value = "用户年龄 [0-150]")
    private Integer userAge;
    
    @ApiModelProperty(value = "用户生日[yyyy-MM-dd]")
    private Date userBirth;
    
    @ApiModelProperty(value = "用户身份证")
    private String userIdCard;
    
    @ApiModelProperty(value = "用户身份证明")
    private String userIdCardImg;
    
    @ApiModelProperty(value = "用户个性签名")
    private String userSign;
}
