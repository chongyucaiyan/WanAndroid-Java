package com.github.cyc.wanandroid.http.base;

/**
 * 自定义的Observer
 */
public interface ObserverX<T> {

    /**
     * 成功时回调
     *
     * @param t 具体的网络请求响应model
     */
    void onNextX(T t);

    /**
     * 出错时回调。与onCompleteX()方法互斥
     *
     * @param errorCode 错误码
     * @param errorMsg  错误信息
     */
    void onErrorX(int errorCode, String errorMsg);

    /**
     * 结束时回调。与onErrorX()方法互斥
     */
    void onCompleteX();

}
