package com.wjq.af.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页-dtoResult
 *
 * @author yixihan
 * @date 2022-09-29-14:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页-dtoResult")
public class PageDtoResult<T> implements Serializable {
    
    private static final long serialVersionUID = -2763957656843891790L;
    
    @ApiModelProperty("当前页")
    private Long current;
    
    @ApiModelProperty("总条数")
    private Long total;
    
    @ApiModelProperty("每页展示条数")
    private Long size;
    
    @ApiModelProperty("pages")
    private Long pages;
    
    @ApiModelProperty("数据")
    private List<T> records;
}

