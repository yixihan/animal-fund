package com.wjq.af.dto.request.rescue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 申请救援动物-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 11:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("申请救援动物-dtoReq")
public class ApplyRescueAnimalDtoReq {
    
    @ApiModelProperty(value = "救援动物信息 ID")
    @NotNull(message = "救援动物信息 ID 不能为空")
    private Long rescueId;
    
    @ApiModelProperty(value = "资金预算")
    @NotNull(message = "资金预算不能为空")
    private BigDecimal capitalBudget;
}
