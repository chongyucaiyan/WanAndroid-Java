package com.github.cyc.wanandroid.module.main.fragment;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentHomepageBinding;
import com.github.cyc.wanandroid.module.main.viewmodel.HomepageViewModel;

/**
 * 首页tab
 */
public class HomepageFragment extends BaseFragment<FragmentHomepageBinding, HomepageViewModel> {

    public static HomepageFragment newInstance() {
        return new HomepageFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new HomepageViewModel();
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
