package com.yubo.springboot.controller.exception;

import com.google.gson.Gson;
import com.yubo.springboot.modal.common.ResponseEntity;
import com.yubo.springboot.modal.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/7/7 14:34
 */
// 该注解表示 开启springboot全局异常捕获
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static Logger logger = LogManager.getLogger(GlobalDefaultExceptionHandler.class);

    // 指定处理的函数类型
    @ExceptionHandler(Exception.class)
    // 如果返回json数据，则使用该注解
    @ResponseBody
    public ResponseEntity handlerException(HttpServletRequest request, Exception exception) {
        logger.info("全局异常路径为：" + request.getRequestURL() + "，异常参数为：" + new Gson().toJson(request.getParameterMap()));
        if (exception instanceof MissingServletRequestParameterException) {
            return ResponseEntity.createError(ControllerException.MISSING_REQUIRED_PARAMETERS);
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            return ResponseEntity.createError(ControllerException.REQUEST_METHOD_ERROR);
        } else {
            return ResponseEntity.createError(ControllerException.SYSTEM_ERROR);
        }
    }
}
