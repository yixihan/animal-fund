package com.wjq.af.dto.request.thirdpart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 审核结果发送-dtoReq
 *
 * @author yixihan
 * @date 2023/2/21 16:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("审核结果发送-dtoReq")
public class ExamineEmailDtoReq {
    
    @ApiModelProperty(value = "收件人")
    private String email;
    
    @ApiModelProperty(value = "审核内容查看网址")
    private String url;
    
    @ApiModelProperty(value = "邮件模板[审核成功: EXAMINE_SUCCESS, 审核失败: EXAMINE_FAIL, 举报成功: REPORT_SUCCESS, 举报失败: REPORT_FAIL]")
    private String type;
}
