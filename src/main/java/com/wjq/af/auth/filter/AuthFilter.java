package com.wjq.af.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjq.af.auth.constant.AuthConstant;
import com.wjq.af.auth.prop.IgnoreUrlsProp;
import com.wjq.af.auth.service.TokenService;
import com.wjq.af.dto.response.JsonResponse;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局过滤器
 *
 * @author yixihan
 * @date 2023/2/16 14:06
 */
@Slf4j
@Component
public class AuthFilter implements Filter {
    
    @Resource
    private IgnoreUrlsProp ignoreUrls;
    
    @Resource
    private TokenService tokenService;
    
    private final AntPathMatcher pathMatcher = new AntPathMatcher ();
    
    /**
     * 过滤器详细规则
     * <p>
     * <li>白名单 : 直接放行</li>
     * <li>跨域的预检请求[Options] : 直接放行</li>
     * <li>请求头无 token : 快速失败 {@code BizCodeEnum.TOKEN_EXPIRED}</li>
     * <li>token 校验不通过 : 快速失败 {@code BizCodeEnum.TOKEN_ERR}</li>
     * <li>权限校验不通过 : 快速失败 {@code BizCodeEnum.NO_METHOD_ROLE}</li>
     * <li>以上校验均通过 : 放行</li>
     * </p>
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        String uri = request.getRequestURI ();
        
        try {
            // 白名单, 直接放行
            if (ignoreUrls.getUrls ().stream ().anyMatch ((item) -> pathMatcher.match (item, uri))) {
                filterChain.doFilter (servletRequest, servletResponse);
                return;
            }
            
            // 对应跨域的预检请求直接放行
            if (HttpMethod.OPTIONS.name ().equals (request.getMethod ())) {
                filterChain.doFilter (servletRequest, servletResponse);
                return;
            }
            
            // 获取 token
            String token = request.getHeader (AuthConstant.JWT_TOKEN);
            if (StrUtil.isBlank (token)) {
                // token 为空， 直接返回
                out (response, BizCodeEnum.TOKEN_EXPIRED);
                return;
            }
            
            // 认证校验
            if (tokenService.authentication (token) == null) {
                out (response, BizCodeEnum.TOKEN_ERR);
                return;
            }
            
            // 权限校验
            if (!tokenService.authorization (token, uri)) {
                out (response, BizCodeEnum.NO_METHOD_ROLE);
                return;
            }
            
            //执行
            filterChain.doFilter (servletRequest, servletResponse);
        } catch (IOException | ServletException | BizException e) {
            log.info ("认证出错 : {}", e.getMessage ());
            out (response, BizCodeEnum.TOKEN_ERR);
        }
    }
    
    /**
     * json 形式返回结果 token 验证失败信息，无需转发
     */
    private void out(ServletResponse response, BizCodeEnum rs) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ObjectMapper mapper = new ObjectMapper ();
        String jsonRes = mapper.writeValueAsString (JsonResponse.error (rs));
        httpServletResponse.setCharacterEncoding ("UTF-8");
        httpServletResponse.setContentType ("application/json; charset=utf-8");
        httpServletResponse.getOutputStream ().write (jsonRes.getBytes ());
    }
    
}
