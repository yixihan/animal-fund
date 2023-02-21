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
 * 救援动物信息状态-dtoResult
 *
 * @author yixihan
 * @date 2023/2/21 11:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("救援动物信息状态-dtoResult")
public class RescueAnimalStatusDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "救援动物信息 ID")
    private Long rescueId;
    
    @ApiModelProperty(value = "救援用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "救援状态[未开始:UN_DO, 审核中: EXAMINING, 募款中:FUNDRAISING, 救援中: RESCUING, 救援成功: SUCCESS, 救援失败:FAIL]")
    private String rescueStatus;
    
    @ApiModelProperty(value = "资金预算")
    private BigDecimal capitalBudget;
    
    @ApiModelProperty(value = "审核状态[待审核: UN_EXAMINE, 审核通过: EXAMINE_SUCCESS, 审核失败:EXAMINE_FAIL]")
    private String examineStatus;
    
    @ApiModelProperty(value = "动物救援前情况(照片)")
    private String animalBeforeRescueImg;
    
    @ApiModelProperty(value = "动物救援中情况(照片)")
    private String animalInRescueImg;
    
    @ApiModelProperty(value = "动物救援后情况(照片)")
    private String animalAfterRescueImg;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
