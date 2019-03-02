package com.github.cyc.wanandroid.base.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.cyc.wanandroid.base.viewmodel.BaseActivityViewModel;

/**
 * Activity的基类
 *
 * @param <DB> data binding
 * @param <VM> view model
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseActivityViewModel>
        extends AppCompatActivity {

    protected DB mDataBinding;

    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
        initViewModel();
        bindViewModel();
        init();
    }

    /**
     * 处理参数
     *
     * @param intent 参数容器
     */
    protected void handleIntent(Intent intent) {

    }

    /**
     * 获取当前页面的布局资源ID
     *
     * @return 布局资源ID
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化ViewModel
     */
    protected abstract void initViewModel();

    /**
     * 绑定ViewModel
     */
    protected abstract void bindViewModel();

    /**
     * 初始化
     */
    protected abstract void init();
}
