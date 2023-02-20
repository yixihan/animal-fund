package com.wjq.af.config.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.Utils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;

/**
 * Druid 配置类
 *
 * @author yixihan
 * @date 2023/2/9 12:41
 */
@Configuration
public class DruidConfig {
    
    private static final HashMap<String, String> MAP;
    
    static {
        MAP = new HashMap<> (16);
        // 可以过滤哪些请求
        
        // 这些东西不进行统计
        MAP.put ("exclusions", "*.js, *.css, /druid/*");
        
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(DruidProp druidProperties) {
        return druidProperties.dataSource (DruidDataSourceBuilder.create ().build ());
    }
    
    
    /**
     * 过滤器, 选择过滤哪些请求
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<> ();
        
        bean.setFilter (new WebStatFilter ());
        bean.setInitParameters (MAP);
        
        return bean;
    }
    
    /**
     * 去除监控页面底部的广告
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
    public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet ();
        
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern () != null ? config.getUrlPattern () : "/druid/*";
        String commonJsPattern = pattern.replaceAll ("\\*", "js/common.js");
        final String filePath = "support/http/resources/js/common.js";
        
        // 创建filter进行过滤
        Filter filter = new Filter () {
            @Override
            public void init(javax.servlet.FilterConfig filterConfig) {
            }
            
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                chain.doFilter (request, response);
                // 重置缓冲区，响应头不会被重置
                response.resetBuffer ();
                // 获取common.js
                String text = Utils.readFromResource (filePath);
                // 正则替换banner, 除去底部的广告信息
                text = text.replaceAll ("<a.*?banner\"></a><br/>", "");
                text = text.replaceAll ("powered.*?shrek.wang</a>", "");
                response.getWriter ().write (text);
            }
            
            @Override
            public void destroy() {
            }
            
        };
        
        FilterRegistrationBean registrationBean = new FilterRegistrationBean ();
        registrationBean.setFilter (filter);
        registrationBean.addUrlPatterns (commonJsPattern);
        return registrationBean;
    }
}
