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
    public void testAdminPassword() {
        String password = MD5Util.md5 ("Theyear123", "90jq4tfh5u");
        System.out.println (password);
    }
    
    @Test
    public void testMatcher() {
        System.out.println (pathMatcher.match ("/druid/**/**", "/druid/js/common.js"));
        System.out.println (pathMatcher.match ("/**/*.js", "/druid/js/common.js"));
    }
    
    @Test
    public void testEmailTemplate() {
        String emailTemplate = "您的审核<span style=\"font-weight:bold\"> 未通过</span>\n" + "  <br>\n" + "  <a href=\"%s\">审核内容</a>\n" + "  <span style=\"font-size: 14px;\">\n" + "    您的审核未通过。若有需要请核对信息后再次提交申请。如有疑问请联系客服管理员。\n" + "  </span>";
        
        String emailContent = String.format (emailTemplate, "https://localhost:18997/index.html");
        System.out.println (emailContent);
    }
}
