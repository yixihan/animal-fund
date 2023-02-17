package com.wjq.af.dto.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户-dtoResult
 *
 * @author yixihan
 * @date 2023/2/15 17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户-dtoResult")
public class UserDtoResult {
    
    @ApiModelProperty(value = "用户 ID")
    private Long id;
    
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date userBirth;
    
    @ApiModelProperty(value = "用户身份证")
    private String userIdCard;
    
    @ApiModelProperty(value = "审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String examineStatus;
    
    @ApiModelProperty(value = "用户个性签名")
    private String userSign;
}
