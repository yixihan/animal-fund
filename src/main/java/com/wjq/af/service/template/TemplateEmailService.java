package com.wjq.af.service.template;

import com.wjq.af.pojo.template.TemplateEmail;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 邮件模板表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-17
 */
public interface TemplateEmailService extends IService<TemplateEmail> {
    
    @PostConstruct
    void initMessageTemplate();
    
    String getEmailContent(Long id);

}
