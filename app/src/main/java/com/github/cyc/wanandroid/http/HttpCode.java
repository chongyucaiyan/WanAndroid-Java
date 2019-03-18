package com.github.cyc.wanandroid.http;

/**
 * HTTP码
 */
public interface HttpCode {

    /**
     * 成功
     */
    int SUCCESS = 0;

    /**
     * 未知错误
     */
    int ERROR_UNKNOWN = 1000;

    /**
     * HTTP错误
     */
    int ERROR_HTTP = 1001;

    /**
     * 网络错误
     */
    int ERROR_NETWORK = 1002;

    /**
     * 解析错误
     */
    int ERROR_PARSE = 1003;

    /**
     * SSL错误
     */
    int ERROR_SSL = 1004;

    /**
     * 登录失效，需要重新登录
     */
    int ERROR_LOGIN_INVALID = -1001;

}
