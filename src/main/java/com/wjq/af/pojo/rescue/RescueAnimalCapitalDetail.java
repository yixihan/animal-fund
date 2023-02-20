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
 * 救援动物资金明细表
 *
 * @author yixihan
 * @since 2023-02-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "RescueAnimalCapitalDetail对象", description = "救援动物资金明细表")
public class RescueAnimalCapitalDetail implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "救援动物信息 ID")
    private Long rescueId;
    
    @ApiModelProperty(value = "资金花费")
    private BigDecimal capitalCost;
    
    @ApiModelProperty(value = "资金明细")
    private String capitalReason;
    
    @ApiModelProperty(value = "资金证明(照片)")
    private String capitalProve;
    
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
    
    public static final String RESCUE_ID = "rescue_id";
    
    public static final String CAPITAL_COST = "capital_cost";
    
    public static final String CAPITAL_REASON = "capital_reason";
    
    public static final String CAPITAL_PROVE = "capital_prove";
    
    public static final String EXAMINE_STATUS = "examine_status";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";
    
}
