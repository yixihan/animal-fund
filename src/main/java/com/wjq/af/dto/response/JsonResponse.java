package com.wjq.af.dto.response;

import cn.hutool.core.util.StrUtil;
import com.wjq.af.exception.BizCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Json 响应数据
 *
 * @author yixihan
 * @date 2023/2/15 15:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Json响应数据")
public class JsonResponse<T> implements Serializable {
    public static final int SUCCESS_CODE = 200;
    private static final long serialVersionUID = -7995058338163551623L;
    @ApiModelProperty("返回码：200为成功")
    private int code;
    
    @ApiModelProperty("异常信息")
    private String message;
    
    @ApiModelProperty("请求ID")
    private String requestId;
    
    @ApiModelProperty("返回数据")
    private T data;
    
    @ApiModelProperty("是否正常返回")
    private boolean success;
    
    @ApiModelProperty("响应参数")
    private Map<String, Object> params;
    
    /**
     * 私有构造方法-成功
     *
     * @param data data
     */
    private JsonResponse(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
        this.success = true;
    }
    
    /**
     * 私有构造方法-失败
     *
     * @param status  status
     * @param message message
     */
    private JsonResponse(int status, String message) {
        this.code = status == 0 ? SUCCESS_CODE : status;
        this.message = message;
        this.success = status == SUCCESS_CODE;
    }
    
    /**
     * JsonResponse-成功
     *
     * @return code = {@value SUCCESS_CODE}, success = true
     */
    public static <T> JsonResponse<T> ok() {
        JsonResponse<T> jsonResponse = new JsonResponse<> ();
        jsonResponse.setCode (SUCCESS_CODE);
        jsonResponse.setSuccess (Boolean.TRUE);
        return jsonResponse;
    }
    
    /**
     * JsonResponse-成功
     *
     * @param data 返回数据
     * @return code = {@value SUCCESS_CODE}, success = true, data = data
     */
    public static <T> JsonResponse<T> ok(T data) {
        return new JsonResponse<> (data);
    }
    
    /**
     * JsonResponse-异常
     *
     * @param message 异常信息
     */
    public static <T> JsonResponse<T> error(String message) {
        return new JsonResponse<> (BizCodeEnum.FAILED_TYPE_INTERNAL.getCode (), message);
    }
    
    /**
     * JsonResponse-异常
     *
     * @param err 业务异常枚举 {@link BizCodeEnum}
     */
    public static <T> JsonResponse<T> error(BizCodeEnum err) {
        return new JsonResponse<> (err.getErrorCode (), err.getErrorMsg ());
    }
    
    /**
     * JsonResponse-异常
     *
     * @param data    返回数据
     * @param message 异常信息
     */
    public static <T> JsonResponse<T> error(T data, String message) {
        JsonResponse<T> jsonResponse = new JsonResponse<> ();
        jsonResponse.setCode (BizCodeEnum.FAILED_TYPE_INTERNAL.getCode ());
        jsonResponse.setSuccess (Boolean.FALSE);
        jsonResponse.setData (data);
        jsonResponse.setMessage (message);
        return jsonResponse;
    }
    
    /**
     * JsonResponse-异常
     *
     * @param errorCode 异常状态码
     * @param message   异常信息
     */
    public static <T> JsonResponse<T> error(int errorCode, String message) {
        return new JsonResponse<> (errorCode, message);
    }
    
    /**
     * JsonResponse-请求异常
     *
     * @param message 异常信息
     */
    public static <T> JsonResponse<T> badRequest(String message) {
        return new JsonResponse<> (BizCodeEnum.FAILED_TYPE_BAD_REQUEST.getCode (), message);
    }
    
    /**
     * 添加参数
     *
     * @param key   key
     * @param value value
     */
    public void addParams(String key, Object value) {
        if (StrUtil.isBlank (key)) {
            return;
        }
        if (null == params) {
            params = new HashMap<> (16);
        }
        params.put (key, value);
    }
}
