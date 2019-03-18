package com.github.cyc.wanandroid.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.github.cyc.wanandroid.app.WanApplication;

/**
 * Toast工具类
 */
public final class ToastUtils {

    private ToastUtils() {

    }

    /**
     * 显示一条Toast
     *
     * @param msg 消息
     */
    public static void show(String msg) {
        Toast.makeText(WanApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示一条Toast
     *
     * @param resId 消息资源ID
     */
    public static void show(@StringRes int resId) {
        Toast.makeText(WanApplication.getInstance(), resId, Toast.LENGTH_SHORT).show();
    }
}
