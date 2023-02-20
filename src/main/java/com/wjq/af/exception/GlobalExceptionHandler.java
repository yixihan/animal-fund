package com.wjq.af.exception;

import com.wjq.af.dto.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获器
 *
 * @author yixihan
 * @date 2023/2/16 9:47
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 通用业务异常捕获
     */
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public JsonResponse<Object> handleBizException(BizException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e.getMessage ());
    }
    
    /**
     * 参数校验异常捕获
     * <br>
     * 针对表单格式校验异常
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonResponse<Object> handleValidException(MethodArgumentNotValidException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e.getMessage ());
    }
    
    /**
     * 参数校验异常捕获
     * <br>
     * 针对 json 格式校验异常
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public JsonResponse<Object> handleValidException(BindException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e.getMessage ());
    }
    
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public JsonResponse<Object> handleNullPointerException(NullPointerException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e.getMessage ());
    }
    
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public JsonResponse<Object> handleRuntimeException(RuntimeException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e.getMessage ());
    }
    
    
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonResponse<Object> handleException(Exception e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e.getMessage ());
    }
}
