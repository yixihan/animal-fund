package com.wjq.af.dto.request.rescue;

import com.wjq.af.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 查询救援动物状态-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询救援动物状态-dtoReq")
public class QueryRescueAnimalStatusDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "救援动物信息 ID")
    private Long rescueId;
    
    @ApiModelProperty(value = "救援用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "救援状态[未开始:UN_DO, 审核中: EXAMINING, 募款中:FUNDRAISING, 救援中: RESCUING, 救援成功: SUCCESS, 救援失败:FAIL]")
    private List<String> rescueStatus;
}
