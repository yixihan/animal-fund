package com.wjq.af.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * CORS 跨域配置类
 *
 * @author yixihan
 * @date 2023/2/9 12:30
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {
    
    /**
     * 解决跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        
        registry
                // 项目中的所有接口都支持跨域
                .addMapping ("/**")
                // 可以访问的具体地址
                .allowedOrigins ("*")
                // 是否允许请求带有验证信息
                .allowCredentials (true).allowedMethods ("GET", "POST", "DELETE", "PUT")
                // 跨域允许时间
                .maxAge (3600);
    }
    
    /**
     * 开放 swagger 页面
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
    }
    
    /**
     * 解决资源过滤问题
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // swagger
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/**/**")
                .addResourceLocations ("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
        // webjars
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/**");
        registry.addResourceHandler("/druid/**")
                .addResourceLocations("classpath:/META-INF/resources/**");
        super.addResourceHandlers(registry);
    }
    
    /**
     * 解决跨域问题
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        // 跨域的配置信息   springframework.web.cors.reactive 包下的
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource ();
        
        CorsConfiguration corsConfiguration = new CorsConfiguration ();
        // 支持哪些来源的请求
        corsConfiguration.addAllowedOrigin ("*");
        // 支持的请求头
        corsConfiguration.addAllowedHeader ("*");
        // 支持哪些请求方式
        corsConfiguration.addAllowedMethod ("*");
        // 是否允许携带 cookie
        corsConfiguration.setAllowCredentials (true);
        
        // 注册跨域的配置信息，任意路径下
        corsConfigurationSource.registerCorsConfiguration ("/**", corsConfiguration);
        
        return new CorsWebFilter (corsConfigurationSource);
    }
}
