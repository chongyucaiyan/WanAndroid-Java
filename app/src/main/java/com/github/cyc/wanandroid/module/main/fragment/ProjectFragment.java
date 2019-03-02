package com.github.cyc.wanandroid.module.main.fragment;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentProjectBinding;
import com.github.cyc.wanandroid.module.main.viewmodel.ProjectViewModel;

/**
 * 项目tab
 */
public class ProjectFragment extends BaseFragment<FragmentProjectBinding, ProjectViewModel> {

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ProjectViewModel();
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
