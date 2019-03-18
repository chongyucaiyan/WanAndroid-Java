package com.github.cyc.wanandroid.app;

import com.github.cyc.wanandroid.data.DataManager;
import com.github.cyc.wanandroid.data.source.HttpDataSource;
import com.github.cyc.wanandroid.http.RetrofitManager;
import com.github.cyc.wanandroid.http.api.ApiService;

/**
 * 依赖注入
 */
public final class Injection {

    private Injection() {

    }

    /**
     * 提供DataManager
     *
     * @return DataManager
     */
    public static DataManager provideDataManager() {
        return DataManager.getInstance(HttpDataSource.getInstance(RetrofitManager.get().create(ApiService.class)));
    }
}
