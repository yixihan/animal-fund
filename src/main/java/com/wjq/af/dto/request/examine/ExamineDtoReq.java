package com.wjq.af.dto.request.examine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 审核-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 14:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("审核-dtoReq")
public class ExamineDtoReq {
    
    @ApiModelProperty(value = "审核内容主键 ID")
    @NotNull(message = "审核内容主键 ID 不能为空")
    private Long id;
    
    @ApiModelProperty(value = "审核结果")
    @NotBlank(message = "审核结果不能为空")
    private String examineStatus;
}
