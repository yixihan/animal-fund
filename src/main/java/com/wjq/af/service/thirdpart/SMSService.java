package com.wjq.af.service.thirdpart;

import com.wjq.af.dto.request.thirdpart.SMSSendDtoReq;
import com.wjq.af.dto.request.thirdpart.SMSValidateDtoReq;
import com.wjq.af.enums.SMSTemplateEnums;

/**
 * 短信 服务类
 *
 * @author yixihan
 * @date 2023/2/18 11:21
 */
public interface SMSService {
    
    /**
     * 发送手机短信
     * <p>
     * 短信发送类型枚举类 : {@link SMSTemplateEnums}
     *
     * @param dtoReq 请求参数
     */
    void send(SMSSendDtoReq dtoReq);
    
    /**
     * 校验短信验证码
     * <p>
     * 短信发送类型枚举类 : {@link SMSTemplateEnums}
     *
     * @param dtoReq 请求参数
     */
    void validate(SMSValidateDtoReq dtoReq);
}
