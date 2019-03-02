package com.github.cyc.wanandroid.module.main.fragment;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentSystemBinding;
import com.github.cyc.wanandroid.module.main.viewmodel.SystemViewModel;

/**
 * 体系tab
 */
public class SystemFragment extends BaseFragment<FragmentSystemBinding, SystemViewModel> {

    public static SystemFragment newInstance() {
        return new SystemFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new SystemViewModel();
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
