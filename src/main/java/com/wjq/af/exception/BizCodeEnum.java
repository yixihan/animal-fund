package com.wjq.af.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举类
 *
 * @author yixihan
 * @date 2023/2/15 15:40
 */
@AllArgsConstructor
@Getter
public enum BizCodeEnum implements CommonError {
    
    /**
     * 失败类型：内部异常
     */
    FAILED_TYPE_INTERNAL (500, "服务器错误"),
    
    /**
     * 请求异常
     */
    FAILED_TYPE_BAD_REQUEST (400, "请求异常"),
    
    /**
     * 未授权
     */
    FAILED_TYPE_UNAUTHORIZED (401, "未授权"),
    
    /**
     * 禁止访问
     */
    FAILED_TYPE_FORBIDDEN (403, "禁止访问"),
    
    /**
     * 业务异常 (默认)
     */
    FAILED_TYPE_BUSINESS (20000, "业务异常"),
    
    /**
     * 参数校验异常
     */
    PARAMS_VALID_ERR (20001, "参数校验异常"),
    
    /**
     * 空指针异常
     */
    NULL_ERR (20002, "空指针异常"),
    
    //============================================================== 认证授权异常
    /**
     * 用户名或密码错误
     */
    PASSWORD_ERR (10001, "密码错误"),
    
    /**
     * 账户已被禁用
     */
    ACCOUNT_DISABLED (10002, "账户已被禁用"),
    
    /**
     * 账户已被锁定
     */
    ACCOUNT_LOCKED (10003, "账户已被锁定"),
    
    /**
     * 账户已过期
     */
    ACCOUNT_EXPIRED (10004, "账户已过期"),
    
    /**
     * 账户不存在
     */
    ACCOUNT_NOT_FOUND (10005, "账户不存在"),
    
    /**
     * 账户凭据已过期
     */
    CREDENTIALS_EXPIRED (10006, "账户凭据已过期"),
    
    /**
     * token 过期
     */
    TOKEN_EXPIRED (10007, "Token已过期, 请重新登录"),
    
    /**
     * token 错误
     */
    TOKEN_ERR (10008, "Token 错误!"),
    
    /**
     * 没有访问权限
     */
    NO_METHOD_ROLE (10009, "没有访问权限"),
    
    //============================================================== 第三方服务
    /**
     * 文件上传失败
     */
    OSS_ERR (90001, "文件上传失败"),
    
    /**
     * 邮件发送失败
     */
    EMAIL_SEND_ERR (90002, "邮件发送失败"),
    
    /**
     * 短信发送失败
     */
    SMS_SEND_ERR (90003, "短信发送失败"),
    
    /**
     * 手机号为空
     */
    MOBILE_EMPTY_ERR (90004, "手机号不能为空"),
    
    /**
     * 邮箱为空
     */
    EMAIL_EMPTY_ERR (90005, "邮箱不能为空"),
    
    /**
     * uuid 为空
     */
    UUID_EMPTY_ERR (90008, "uuid不能为空"),
    
    /**
     * 验证码为空
     */
    CODE_EMPTY_ERR (90006, "验证码不能为空"),
    
    /**
     * 验证码错误
     */
    CODE_VALIDATE_ERR (90007, "验证码错误"),
    
    ;
    
    
    /**
     * 状态码
     */
    private final int code;
    
    /**
     * 异常信息
     */
    private final String message;
    
    @Override
    public int getErrorCode() {
        return code;
    }
    
    @Override
    public String getErrorMsg() {
        return message;
    }
}
