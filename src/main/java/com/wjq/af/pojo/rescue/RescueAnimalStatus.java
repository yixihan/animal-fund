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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 救援动物状态表
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RescueAnimalStatus对象", description = "救援动物状态表")
public class RescueAnimalStatus implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "救援动物信息 ID")
    private Long rescueId;
    
    @ApiModelProperty(value = "救援用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "救援状态[未开始:UN_DO, 审核中: EXAMINING, 募款中:FUNDRAISING, 救援中: RESCUING, 救援成功: SUCCESS, 救援失败:FAIL]")
    private String rescueStatus;
    
    @ApiModelProperty(value = "资金预算")
    private BigDecimal capitalBudget;
    
    @ApiModelProperty(value = "审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String examineStatus;
    
    @ApiModelProperty(value = "动物救援前情况(照片)")
    private String animalBeforeRescueImg;
    
    @ApiModelProperty(value = "动物救援中情况(照片)")
    private String animalInRescueImg;
    
    @ApiModelProperty(value = "动物救援后情况(照片)")
    private String animalAfterRescueImg;
    
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
    
    public static final String RESCUE_ID = "rescue_id";
    
    public static final String USER_ID = "user_id";
    
    public static final String RESCUE_STATUS = "rescue_status";
    
    public static final String CAPITAL_BUDGET = "capital_budget";
    
    public static final String EXAMINE_STATUS = "examine_status";
    
    public static final String ANIMAL_BEFORE_RESCUE_IMG = "animal_before_rescue_img";
    
    public static final String ANIMAL_IN_RESCUE_IMG = "animal_in_rescue_img";
    
    public static final String ANIMAL_AFTER_RESCUE_IMG = "animal_after_rescue_img";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";
    
}
