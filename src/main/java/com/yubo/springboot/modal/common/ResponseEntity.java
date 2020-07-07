package com.yubo.springboot.modal.common;

import com.yubo.springboot.modal.exception.ControllerException;

/**
 * @author yubo
 * @version V1.0
 * @description 统一数据返回各式
 * @date 2020/7/7 14:01
 */
public class ResponseEntity<T> {

    private boolean success;

    private String requestOrder;

    private T data;

    private Integer errorCode;

    private String error;

    private String errorDesc;

    public ResponseEntity() {
    }

    /**
     * 请求成功，正常返回数据
     *
     * @param success
     * @param requestOrder
     * @param data
     */
    public ResponseEntity(boolean success, String requestOrder, T data) {
        this.success = success;
        this.requestOrder = requestOrder;
        this.data = data;
    }

    public static <T> ResponseEntity<T> createError(ControllerException controllerException) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.success = false;
        responseEntity.setErrorCode(controllerException.getCode());
        responseEntity.setError(controllerException.name());
        responseEntity.setErrorDesc(controllerException.getMessage());
        return responseEntity;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRequestOrder() {
        return requestOrder;
    }

    public void setRequestOrder(String requestOrder) {
        this.requestOrder = requestOrder;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
