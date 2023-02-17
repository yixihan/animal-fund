package com.wjq.af;

import com.wjq.af.utils.MD5Util;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * description
 *
 * @author yixihan
 * @date 2023/2/15 17:38
 */
public class CustomTest {
    
    private final AntPathMatcher pathMatcher = new AntPathMatcher ();
    
    @Test
    public void testAdminPassword () {
        String password = MD5Util.md5 ("Theyear123", "90jq4tfh5u");
        System.out.println (password);
    }
    
    @Test
    public void testMatcher () {
        System.out.println (pathMatcher.match ("/druid/**/**", "/druid/js/common.js"));
        System.out.println (pathMatcher.match ("/**/*.js", "/druid/js/common.js"));
    }
}
