package com.wjq.af.service.thirdpart.impl;

import com.wjq.af.config.prop.CodeProp;
import com.wjq.af.config.prop.EmailProp;
import com.wjq.af.dto.request.thirdpart.EmailSendDtoReq;
import com.wjq.af.dto.request.thirdpart.EmailValidateDtoReq;
import com.wjq.af.enums.EmailTemplateEnums;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.service.template.TemplateEmailService;
import com.wjq.af.service.thirdpart.CodeService;
import com.wjq.af.service.thirdpart.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务实现类
 *
 * @author yixihan
 * @date 2023/2/17 15:58
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    
    @Resource
    private JavaMailSender mailSender;
    
    @Resource
    private CodeService codeService;
    
    @Resource
    private TemplateEmailService templateEmailService;
    
    @Resource
    private CodeProp codeProp;
    
    @Resource
    private EmailProp emailProp;
    
    @Override
    public void emailSend(EmailSendDtoReq dtoReq) {
        EmailTemplateEnums emailType = EmailTemplateEnums.valueOf (dtoReq.getType ());
        String keyName = getRedisKey (dtoReq.getEmail (), emailType);
        String emailContent = templateEmailService.getEmailContent (emailType.getId ());
        try {
            String code = codeService.getCode(keyName);
            // 创建一个复杂的文件
            MimeMessage mailMessage = mailSender.createMimeMessage();
            // 组装邮件
            MimeMessageHelper helper = new MimeMessageHelper (mailMessage,true,"utf-8");
            helper.setSubject(emailProp.getTitle ());
            helper.setText(String.format (emailContent, code, codeProp.getTimeOut ()),true);
            // 收件人
            helper.setTo(dtoReq.getEmail ());
            // 发件人
            helper.setFrom(emailProp.getSendEmail ());
            // 发送
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            log.error ("邮件发送失败 : {}", e.getMessage ());
            throw new BizException (BizCodeEnum.EMAIL_SEND_ERR);
        }
    }
    
    @Override
    public void emailValidate(EmailValidateDtoReq dtoReq) {
        // 生成 keyName
        EmailTemplateEnums emailType = EmailTemplateEnums.valueOf (dtoReq.getEmailType ());
        String keyName = getRedisKey (dtoReq.getEmail (), emailType);
        codeService.validate (keyName, dtoReq.getCode ());
    }
    
    /**
     * 获取 redis key
     *
     * @param email email
     * @param emailType 邮箱类型 {@link EmailTemplateEnums}
     * @return redis key
     */
    private String getRedisKey (String email, EmailTemplateEnums emailType) {
        String key;
        switch (emailType) {
            case EXAMINE:
                key = String.format (emailProp.getExamineKey (), email);
                break;
            case REPORT:
                key = String.format (emailProp.getReportKey (), email);
                break;
            case RESET_PASSWORD:
                key = String.format (emailProp.getUpdatePasswordKey (), email);
                break;
            case COMMON:
                key = String.format (emailProp.getCommonKey (), email);
                break;
            default:
                throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        return key;
    }
}
