package com.wjq.af.dto.request.comment;

import com.wjq.af.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 留言回复明细-dtoReq
 *
 * @author yixihan
 * @date 2023/1/11 11:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("留言回复明细-dtoReq")
public class SonCommentDetailDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "父留言 id")
    private Long rootId;
}
