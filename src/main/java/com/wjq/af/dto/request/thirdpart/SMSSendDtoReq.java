package com.wjq.af.dto.request.thirdpart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 手机短信发送-dtoReq
 *
 * @author yixihan
 * @date 2023/2/17 15:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("手机短信发送-dtoReq")
public class SMSSendDtoReq {
    
    @ApiModelProperty("手机号")
    private String mobile;
    
    @ApiModelProperty("发送类型")
    private String type;
    
}
