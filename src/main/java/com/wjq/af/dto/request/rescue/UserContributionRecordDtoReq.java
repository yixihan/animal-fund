package com.wjq.af.dto.request.rescue;

import com.wjq.af.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户捐款记录-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 9:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户捐款记录-dtoReq")
public class UserContributionRecordDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "捐款用户 ID")
    private Long userId;
}
