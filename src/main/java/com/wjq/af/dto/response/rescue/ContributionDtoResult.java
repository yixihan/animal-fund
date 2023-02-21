package com.wjq.af.dto.response.rescue;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 捐款明细-dtoResult
 *
 * @author yixihan
 * @date 2023/2/21 9:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("捐款明细-dtoResult")
public class ContributionDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "救援动物信息 ID")
    private Long rescueId;
    
    @ApiModelProperty(value = "捐款用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "捐款数额")
    private BigDecimal contributionMoney;
    
    @ApiModelProperty(value = "捐款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
