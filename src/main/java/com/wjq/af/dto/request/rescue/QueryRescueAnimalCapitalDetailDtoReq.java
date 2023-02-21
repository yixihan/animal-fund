package com.wjq.af.dto.request.rescue;

import com.wjq.af.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索救援动物资金明细-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索救援动物资金明细-dtoReq")
public class QueryRescueAnimalCapitalDetailDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "救援动物信息 ID")
    private Long rescueId;
}
