package com.github.cyc.wanandroid.utils;

import java.util.List;

/**
 * 工具类
 */
public final class Utils {

    private Utils() {

    }

    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return true表示为空，false表示不为空
     */
    public static boolean isListEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }
}
