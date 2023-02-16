package com.wjq.af.exception;

/**
 * 错误码和错误信息定义类
 *
 * @author yixihan
 * @date 2023/2/15 15:39
 */
public interface CommonError {
    
    /**
     * 错误码
     *
     * @return 错误码
     */
    int getErrorCode();
    
    /**
     * 错误信息
     *
     * @return 错误信息
     */
    String getErrorMsg();
}
