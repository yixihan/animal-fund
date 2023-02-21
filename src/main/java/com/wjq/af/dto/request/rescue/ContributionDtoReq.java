package com.wjq.af.dto.request.rescue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 捐款请求-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 9:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("捐款请求-dtoReq")
public class ContributionDtoReq {
    
    @ApiModelProperty(value = "救援动物信息 ID")
    @NotNull(message = "救援动物信息 ID 不能为空")
    private Long rescueId;
    
    @ApiModelProperty(value = "捐款数额")
    @NotNull(message = "捐款数额不能为空")
    private BigDecimal contributionMoney;
}
