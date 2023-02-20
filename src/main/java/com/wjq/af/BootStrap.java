package com.wjq.af;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.Arrays;

/**
 * animal fund 主启动类
 * <p>
 * EnableAsync 启用异步任务<br>
 * EnableOpenApi 启用 swagger api doc 页面<br>
 * EnableScheduling 启用定时任务<br>
 * EnableTransactionManagement 启用事务<br>
 * </p>
 *
 * @author yixihan
 * @date 2023-02-08 14:54
 */
@Slf4j
@EnableAsync
@EnableOpenApi
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class BootStrap {
    
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication (BootStrap.class);
        Environment env = springApplication.run (args).getEnvironment ();
        log.info ("animal fund server has started : {}, CPU core : {}",
                Arrays.toString (env.getActiveProfiles ()), Runtime.getRuntime ().availableProcessors ());
    }
    
}
