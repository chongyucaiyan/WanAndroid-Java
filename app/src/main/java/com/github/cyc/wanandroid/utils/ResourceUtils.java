package com.github.cyc.wanandroid.utils;

import android.support.annotation.StringRes;

import com.github.cyc.wanandroid.app.WanApplication;

/**
 * 资源工具类
 */
public final class ResourceUtils {

    private ResourceUtils() {

    }

    /**
     * 获取字符串资源
     *
     * @param resId 字符串资源ID
     * @return 字符串
     */
    public static String getString(@StringRes int resId) {
        return WanApplication.getInstance().getString(resId);
    }
}
