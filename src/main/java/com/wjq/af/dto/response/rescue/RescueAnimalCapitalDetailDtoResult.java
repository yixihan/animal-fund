package com.wjq.af.dto.response.rescue;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 救援动物资金明细-dtoResult
 *
 * @author yixihan
 * @date 2023/2/21 14:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("救援动物资金明细-dtoResult")
public class RescueAnimalCapitalDetailDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
