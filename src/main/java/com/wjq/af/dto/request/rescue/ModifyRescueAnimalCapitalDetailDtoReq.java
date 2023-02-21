package com.wjq.af.dto.request.rescue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 修改救援动物资金明细-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 14:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改救援动物资金明细-dtoReq")
public class ModifyRescueAnimalCapitalDetailDtoReq {
    
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
}
