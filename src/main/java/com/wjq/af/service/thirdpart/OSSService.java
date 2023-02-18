package com.wjq.af.service.thirdpart;

import com.wjq.af.dto.request.thirdpart.OSSPolicyDtoReq;

import java.util.Map;

/**
 * oss 服务类
 *
 * @author yixihan
 * @date 2022-10-24-15:19
 */
public interface OSSService {

    /**
     * 获取上传密钥
     *
     * @param req 请求参数
     */
    Map<String, String> policy(OSSPolicyDtoReq req);
}
