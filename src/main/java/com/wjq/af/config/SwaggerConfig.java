package com.wjq.af.config;

import com.wjq.af.auth.prop.AuthProp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * Swagger 配置类
 *
 * @author yixihan
 * @date 2023/2/9 12:35
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Resource
    private AuthProp prop;
    
    /**
     * 配置 Swagger 的 Docket 实例
     */
    @Bean
    public Docket docket() {
        return new Docket (DocumentationType.OAS_30)
                // 限制只搜索指定包下的 api
                .select ()
                .apis (RequestHandlerSelectors.basePackage (prop.getControllerPackage ()))
                .build ()
                // 创建该 API 的基本信息
                .apiInfo (apiInfo ());
        
    }
    
    
    /**
     * 配置 Swagger 的信息
     *
     * @return apiInfo
     */
    private ApiInfo apiInfo() {
        
        // 作者信息
        Contact authorInfo = new Contact ("yixihan", "https://github.com/yixihan", "3113788997@qq.com");
        
        return new ApiInfoBuilder ()
                // 设置标题
                .title ("罐头基金 - swagger 接口文档")
                // 设置描述
                .description ("罐头基金——流浪动物的救助筹款系统的设计与实现")
                // 设置作者信息
                .contact (authorInfo)
                .build ();
    }
}
