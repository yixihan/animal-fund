package com.wjq.af.service.thirdpart;

import com.wjq.af.dto.request.thirdpart.EmailSendDtoReq;
import com.wjq.af.dto.request.thirdpart.EmailValidateDtoReq;

/**
 * 邮件 服务类
 *
 * @author yixihan
 * @date 2023/2/17 15:57
 */
public interface EmailService {
    
    /**
     * 邮件发送
     *
     * @param dtoReq 请求参数
     */
    void send(EmailSendDtoReq dtoReq);
    
    /**
     * 邮件验证码验证
     *
     * @param dtoReq 请求参数
     */
    void validate(EmailValidateDtoReq dtoReq);
}
