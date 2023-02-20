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
 * 救援动物捐款信息表
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RescueAnimalContribution对象", description = "救援动物捐款信息表")
public class RescueAnimalContribution implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "救援动物信息 ID")
    private Long rescueId;
    
    @ApiModelProperty(value = "捐款用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "捐款数额")
    private BigDecimal contributionMoney;
    
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
    
    public static final String CONTRIBUTION_MONEY = "contribution_money";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";
    
}
