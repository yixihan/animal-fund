package com.wjq.af.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis Plus 配置类
 * <p>
 * MapperScan("mapper") 指定 mapper 包路径<br>
 * EnableTransactionManagement 启用事务支持<br>
 * </p>
 *
 * @author yixihan
 * @date 2023/2/9 12:32
 */
@Configuration
@MapperScan("com.wjq.af.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {
    
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor ();
        
        // MP 分页插件
        mybatisPlusInterceptor.addInnerInterceptor (new PaginationInnerInterceptor (DbType.MYSQL));
        
        // MP 乐观锁
        mybatisPlusInterceptor.addInnerInterceptor (new OptimisticLockerInnerInterceptor ());
        
        return mybatisPlusInterceptor;
    }
}
