package com.wjq.af;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootStrapTests {
    
    @Value("${animal.name}")
    private String name;
    
    @Value("${animal.redis.db}")
    private Integer redisDb;
    
    @Test
    void contextLoads() {
        System.out.println (name);
        System.out.println (redisDb);
    }
    
}
