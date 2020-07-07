package com.yubo.springboot.modal.exception;

/**
 * @author yubo
 * @version V1.0
 * @description 异常枚举
 * @date 2020/7/7 13:56
 */
public enum ControllerException {

    SYSTEM_ERROR("系统异常", 10000005),
    MOCK_TYPE_ERROR("模拟数据标识错误", 10000006),
    MISSING_REQUIRED_PARAMETERS("缺失必填参数", 10000007),
    REQUEST_METHOD_ERROR("请求方式错误", 10000008);

    ControllerException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    private String message;

    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
