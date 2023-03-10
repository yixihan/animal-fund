package com.wjq.af.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页请求-dtoReq
 *
 * @author yixihan
 * @date 2022-09-29-14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页请求-dtoReq")
public class PageDtoReq implements Serializable {
    
    private static final long serialVersionUID = 7234584781842150083L;
    
    @ApiModelProperty("页码")
    private Long page = 0L;
    
    @ApiModelProperty("分页大小")
    private Long pageSize = 10L;
    
    @ApiModelProperty("是否搜索总数据条数")
    private Boolean searchCount = true;
}
