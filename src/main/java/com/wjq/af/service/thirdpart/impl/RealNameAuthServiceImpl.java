package com.wjq.af.service.thirdpart.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wjq.af.config.prop.RealNameAuthProp;
import com.wjq.af.constant.NumberConstant;
import com.wjq.af.dto.request.thirdpart.RealNameAuthDtoReq;
import com.wjq.af.exception.BizCodeEnum;
import com.wjq.af.exception.BizException;
import com.wjq.af.service.thirdpart.RealNameAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 实名认证 服务实现类
 *
 * @author yixihan
 * @date 2023/2/20 13:36
 */
@Slf4j
@Service
public class RealNameAuthServiceImpl implements RealNameAuthService {
    
    @Resource
    private RealNameAuthProp prop;
    
    private static final String AUTHORIZATION_CONTENT = "APPCODE %s";
    
    @Override
    public void auth(RealNameAuthDtoReq req) {
        Map<String, Object> requestBody = new HashMap<> ();
        requestBody.put ("name", req.getRealName ());
        requestBody.put ("id_number", req.getIdCard ());
    
        
        try (HttpResponse response = HttpRequest.post (prop.getUrl ())
                .auth (String.format (AUTHORIZATION_CONTENT, prop.getAppCode ()))
                .contentType ("application/x-www-form-urlencoded; charset=UTF-8")
                .form (requestBody)
                .execute ()) {
    
            // 获取 http status 400 表示库无
            int status = response.getStatus ();
            if (status == 400) {
                throw new  BizException("库无");
            }
            
            JSONObject body = JSONUtil.parseObj (response.body ());
            log.info ("body : {}", body);
            Integer state = body.getInt ("state");
            // 查询成功, 但 state != 1
            if (!NumberConstant.NUM_1.equals (state)) {
                throw new BizException (body.getStr ("result_message"));
            }
        } catch (Exception e) {
            if (e instanceof BizException) {
                throw e;
            } else {
                throw new BizException (BizCodeEnum.FAILED_TYPE_BUSINESS);
            }
        }
        
    }
}
