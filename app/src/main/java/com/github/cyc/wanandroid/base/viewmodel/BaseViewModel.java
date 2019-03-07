package com.github.cyc.wanandroid.base.viewmodel;

import android.databinding.ObservableField;

import com.github.cyc.wanandroid.base.enums.LoadState;

/**
 * ViewModel的基类
 */
public abstract class BaseViewModel {

    public final ObservableField<LoadState> loadState = new ObservableField<>();

    /**
     * 获取加载状态
     *
     * @return 加载状态
     */
    public LoadState getLoadState() {
        return loadState.get();
    }

    /**
     * 重新加载。没有网络，点击重试时回调
     */
    public void reload() {
        loadState.set(LoadState.LOADING);
    }
}
