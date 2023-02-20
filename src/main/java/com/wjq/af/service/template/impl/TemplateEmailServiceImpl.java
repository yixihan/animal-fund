package com.wjq.af.service.template.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.af.constant.RedisKeyConstant;
import com.wjq.af.mapper.template.TemplateEmailMapper;
import com.wjq.af.pojo.template.TemplateEmail;
import com.wjq.af.service.template.TemplateEmailService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 邮件模板表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-02-17
 */
@Service
public class TemplateEmailServiceImpl extends ServiceImpl<TemplateEmailMapper, TemplateEmail> implements TemplateEmailService {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 将数据库种的所有模板提取到 Redis 中<br>
     * 执行频率
     * <li>项目启动时执行一次</li>
     * <li>每隔十分钟执行一次</li>
     */
    @Override
    @PostConstruct
    @Scheduled(cron = "0 0/10 * * * ?")
    public void initMessageTemplate() {
        List<TemplateEmail> templateList = baseMapper.selectList (null);
        JSONArray array = JSONUtil.createArray ();
        array.addAll (templateList);
        redisTemplate.opsForValue ().set (RedisKeyConstant.EMAIL_TEMPLATE_KEY, array);
    }
    
    @Override
    public String getEmailContent(Long id) {
        String template;
        try {
            String jsonStr = JSONUtil.toJsonStr (redisTemplate.opsForValue ()
                    .get (RedisKeyConstant.EMAIL_TEMPLATE_KEY));
            template = JSONUtil.parseArray (jsonStr)
                    .toList (TemplateEmail.class)
                    .stream ()
                    .filter ((o) -> o.getId ().equals (id))
                    .findFirst ()
                    .orElse (baseMapper.selectById (id)).getTemplateContent ();
        } catch (Exception e) {
            template = baseMapper.selectById (id).getTemplateContent ();
        }
        return template;
    }
    
}
