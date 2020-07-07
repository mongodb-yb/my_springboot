package com.yubo.springboot.error;

import com.yubo.springboot.modal.exception.ControllerException;

/**
 * @author yubo
 * @version V1.0
 * @description 自定义业务异常模板类
 * @date 2020/7/7 13:33
 */
public class BusinessException extends RuntimeException {

    private ControllerException controllerException;

    public BusinessException() {
    }

    public BusinessException(ControllerException controllerException) {
        this.controllerException = controllerException;
    }

    public BusinessException(String s, ControllerException controllerException) {
        super(s);
        this.controllerException = controllerException;
    }
}
