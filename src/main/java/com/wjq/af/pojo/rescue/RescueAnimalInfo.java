package com.wjq.af.pojo.rescue;

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
 * 救援动物信息表
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RescueAnimalInfo对象", description = "救援动物信息表")
public class RescueAnimalInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "申请用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "动物种类")
    private String animalType;
    
    @ApiModelProperty(value = "动物性别[雄性:MALE, 雌性:FEMALE]")
    private String animalGender;
    
    @ApiModelProperty(value = "受伤程度[]")
    private String injuryDegree;
    
    @ApiModelProperty(value = "地点")
    private String address;
    
    @ApiModelProperty(value = "具体地点")
    private String addressDetail;
    
    @ApiModelProperty(value = "申请原因")
    private String applyReason;
    
    @ApiModelProperty(value = "申请照片")
    private String applyImg;
    
    @ApiModelProperty(value = "审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String examineStatus;
    
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
    
    public static final String USER_ID = "user_id";
    
    public static final String ANIMAL_TYPE = "animal_type";
    
    public static final String ANIMAL_GENDER = "animal_gender";
    
    public static final String INJURY_DEGREE = "injury_degree";
    
    public static final String ADDRESS = "address";
    
    public static final String ADDRESS_DETAIL = "address_detail";
    
    public static final String APPLY_REASON = "apply_reason";
    
    public static final String APPLY_IMG = "apply_img";
    
    public static final String EXAMINE_STATUS = "examine_status";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";
    
}
