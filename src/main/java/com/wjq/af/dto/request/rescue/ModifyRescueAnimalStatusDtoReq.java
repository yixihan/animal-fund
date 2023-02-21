package com.wjq.af.dto.request.rescue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 修改救援动物状态-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改救援动物状态-dtoReq")
public class ModifyRescueAnimalStatusDtoReq {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "资金预算")
    private BigDecimal capitalBudget;
    
    @ApiModelProperty(value = "动物救援前情况(照片)")
    private String animalBeforeRescueImg;
    
    @ApiModelProperty(value = "动物救援中情况(照片)")
    private String animalInRescueImg;
    
    @ApiModelProperty(value = "动物救援后情况(照片)")
    private String animalAfterRescueImg;
}
