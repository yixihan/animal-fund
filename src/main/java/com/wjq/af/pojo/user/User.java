package com.wjq.af.pojo.user;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author yixihan
 * @since 2023-02-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "用户 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "用户手机号")
    private String userMobile;
    
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;
    
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    
    @ApiModelProperty(value = "用户盐")
    private String userSalt;
    
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
    
    @ApiModelProperty(value = "审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String examineStatus;
    
    @ApiModelProperty(value = "用户个性签名")
    private String userSign;
    
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;
    
    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer delFlag;
    
    
    public static final String ID = "id";
    
    public static final String USER_MOBILE = "user_mobile";
    
    public static final String USER_EMAIL = "user_email";
    
    public static final String USER_SALT = "user_salt";
    
    public static final String USER_PASSWORD = "user_password";
    
    public static final String USER_FULL_NAME = "user_full_name";
    
    public static final String USER_NICK_NAME = "user_nick_name";
    
    public static final String USER_AVATAR = "user_avatar";
    
    public static final String USER_GENDER = "user_gender";
    
    public static final String USER_AGE = "user_age";
    
    public static final String USER_BIRTH = "user_birth";
    
    public static final String USER_ID_CARD = "user_id_card";
    
    public static final String USER_ID_CARD_IMG = "user_id_card_img";
    
    public static final String EXAMINE_STATUS = "examine_status";
    
    public static final String USER_SIGN = "user_sign";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";
    
}
