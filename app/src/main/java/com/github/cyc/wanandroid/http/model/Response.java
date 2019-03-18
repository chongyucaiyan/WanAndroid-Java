package com.github.cyc.wanandroid.http.model;

import com.github.cyc.wanandroid.app.NoProguard;

/**
 * 网络请求响应model
 *
 * @param <T> 具体的响应model
 */
public class Response<T> implements NoProguard {

    /**
     * 错误码。0表示成功，非0表示失败
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 具体的响应model
     */
    private T data;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
