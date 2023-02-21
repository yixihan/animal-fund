package com.wjq.af.dto.request.rescue;

import com.wjq.af.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 救援动物捐款记录-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 9:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("救援动物捐款记录-dtoReq")
public class AnimalContributionRecordDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "救援动物信息 ID")
    @NotNull(message = "救援动物信息 ID 不能为空")
    private Long rescueId;
}
