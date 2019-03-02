package com.github.cyc.wanandroid.module.main.fragment;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentNavigationBinding;
import com.github.cyc.wanandroid.module.main.viewmodel.NavigationViewModel;

/**
 * 导航tab
 */
public class NavigationFragment extends BaseFragment<FragmentNavigationBinding, NavigationViewModel> {

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new NavigationViewModel();
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
