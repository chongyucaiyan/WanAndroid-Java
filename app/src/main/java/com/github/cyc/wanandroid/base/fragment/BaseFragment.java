package com.github.cyc.wanandroid.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel;
import com.github.cyc.wanandroid.databinding.FragmentBaseBinding;
import com.github.cyc.wanandroid.databinding.ViewLoadErrorBinding;
import com.github.cyc.wanandroid.databinding.ViewLoadingBinding;
import com.github.cyc.wanandroid.databinding.ViewNoDataBinding;
import com.github.cyc.wanandroid.databinding.ViewNoNetworkBinding;
import com.github.cyc.wanandroid.enums.LoadState;

/**
 * Fragment的基类
 *
 * @param <DB> data binding
 * @param <VM> view model
 */
public abstract class BaseFragment<DB extends ViewDataBinding, VM extends BaseViewModel>
        extends Fragment {

    protected DB mDataBinding;

    protected VM mViewModel;

    private FragmentBaseBinding mFragmentBaseBinding;

    private ViewLoadingBinding mViewLoadingBinding;

    private ViewLoadErrorBinding mViewLoadErrorBinding;

    private ViewNoNetworkBinding mViewNoNetworkBinding;

    private ViewNoDataBinding mViewNoDataBinding;

    private Observable.OnPropertyChangedCallback mLoadStateCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            handleArguments(args);
        }

        initViewModel();

        // ViewModel订阅生命周期事件
        if (mViewModel != null) {
            getLifecycle().addObserver(mViewModel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false);
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutResId(),
                mFragmentBaseBinding.flContentContainer, true);

        bindViewModel();

        initLoadState();
        init();

        return mFragmentBaseBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mViewModel != null && isSupportLoad()) {
            mViewModel.loadState.removeOnPropertyChangedCallback(mLoadStateCallback);
        }
    }

    private void initLoadState() {
        if (mViewModel != null && isSupportLoad()) {
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
        int childCount = mFragmentBaseBinding.flContentContainer.getChildCount();
        if (childCount > 1) {
            mFragmentBaseBinding.flContentContainer.removeViews(1, childCount - 1);
        }
    }

    private void switchLoadView(LoadState loadState) {
        removeLoadView();

        switch (loadState) {
            case LOADING:
                if (mViewLoadingBinding == null) {
                    mViewLoadingBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_loading,
                            mFragmentBaseBinding.flContentContainer, false);
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewLoadingBinding.getRoot());
                break;

            case NO_NETWORK:
                if (mViewNoNetworkBinding == null) {
                    mViewNoNetworkBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_network,
                            mFragmentBaseBinding.flContentContainer, false);
                    mViewNoNetworkBinding.setViewModel(mViewModel);
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewNoNetworkBinding.getRoot());
                break;

            case NO_DATA:
                if (mViewNoDataBinding == null) {
                    mViewNoDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_no_data,
                            mFragmentBaseBinding.flContentContainer, false);
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewNoDataBinding.getRoot());
                break;

            case ERROR:
                if (mViewLoadErrorBinding == null) {
                    mViewLoadErrorBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_load_error,
                            mFragmentBaseBinding.flContentContainer, false);
                }
                mFragmentBaseBinding.flContentContainer.addView(mViewLoadErrorBinding.getRoot());
                break;

            default:
                break;
        }
    }

    /**
     * 处理参数
     *
     * @param args 参数容器
     */
    protected void handleArguments(Bundle args) {

    }

    /**
     * 是否支持页面加载。默认不支持
     *
     * @return true表示支持，false表示不支持
     */
    protected boolean isSupportLoad() {
        return false;
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
