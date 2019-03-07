package com.github.cyc.wanandroid.base.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.enums.LoadState;
import com.github.cyc.wanandroid.base.viewmodel.BaseActivityViewModel;
import com.github.cyc.wanandroid.databinding.ActivityBaseBinding;
import com.github.cyc.wanandroid.databinding.ViewLoadErrorBinding;
import com.github.cyc.wanandroid.databinding.ViewLoadingBinding;
import com.github.cyc.wanandroid.databinding.ViewNoDataBinding;
import com.github.cyc.wanandroid.databinding.ViewNoNetworkBinding;

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

    private ActivityBaseBinding mActivityBaseBinding;

    private ViewLoadingBinding mViewLoadingBinding;

    private ViewLoadErrorBinding mViewLoadErrorBinding;

    private ViewNoNetworkBinding mViewNoNetworkBinding;

    private ViewNoDataBinding mViewNoDataBinding;

    private Observable.OnPropertyChangedCallback mLoadStateCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());

        mActivityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutResId(),
                mActivityBaseBinding.flContentContainer, true);

        initViewModel();
        bindViewModel();

        initLoadState();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.loadState.removeOnPropertyChangedCallback(mLoadStateCallback);
        }
    }

    private void initLoadState() {
        if (mViewModel != null) {
            mLoadStateCallback = new Observable.OnPropertyChangedCallback() {

                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    switchLoadView(mViewModel.getLoadState());
                }
            };
            mViewModel.loadState.addOnPropertyChangedCallback(mLoadStateCallback);
        }
    }

    private void removeLoadView() {
        int childCount = mActivityBaseBinding.flContentContainer.getChildCount();
        if (childCount > 1) {
            mActivityBaseBinding.flContentContainer.removeViews(1, childCount - 1);
        }
    }

    private void switchLoadView(LoadState loadState) {
        removeLoadView();

        switch (loadState) {
            case LOADING:
                if (mViewLoadingBinding == null) {
                    mViewLoadingBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_loading,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadingBinding.getRoot());
                break;

            case NO_NETWORK:
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_network,
                            mActivityBaseBinding.flContentContainer, false);
                    mViewNoNetworkBinding.setViewModel(mViewModel);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoNetworkBinding.getRoot());
                break;

            case NO_DATA:
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_data,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewNoDataBinding.getRoot());
                break;

            case ERROR:
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_load_error,
                            mActivityBaseBinding.flContentContainer, false);
                }
                mActivityBaseBinding.flContentContainer.addView(mViewLoadErrorBinding.getRoot());
                break;

            default:
                break;
        }
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
