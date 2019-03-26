package com.github.cyc.wanandroid.base.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel;

/**
 * 懒加载Fragment的基类
 *
 * @param <DB> data binding
 * @param <VM> view model
 */
public abstract class BaseLazyFragment<DB extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseFragment<DB, VM> {

    protected boolean mVisibleToUser;

    protected boolean mViewCreated;

    protected boolean mLazyLoaded;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mVisibleToUser = isVisibleToUser;
        lazyLoad();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewCreated = true;
        lazyLoad();
    }

    private void lazyLoad() {
        if (mVisibleToUser && mViewCreated && !mLazyLoaded) {
            mLazyLoaded = true;
            onLazyLoad();
        }
    }

    /**
     * 懒加载数据
     */
    protected abstract void onLazyLoad();
}
