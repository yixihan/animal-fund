package com.wjq.af.service.thirdpart;

import com.wjq.af.dto.request.thirdpart.RealNameAuthDtoReq;

/**
 * 实名认证 服务类
 *
 * @author yixihan
 * @date 2023/2/20 13:32
 */
public interface RealNameAuthService {
    
    /**
     * 实名认证
     * <br>
     * <a href="https://market.aliyun.com/products/57000002/cmapi00035880.html?spm=5176.2020520132.101.3.3c5f7218OVp1Tc#sku=yuncode2988000001">参考文档</a>
     *
     * @param req 请求参数
     */
    void auth (RealNameAuthDtoReq req);
}
