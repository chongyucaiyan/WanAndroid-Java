package com.github.cyc.wanandroid.module.main.fragment;

import android.support.v4.app.Fragment;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Injection;
import com.github.cyc.wanandroid.app.ScrollToTop;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentProjectBinding;
import com.github.cyc.wanandroid.module.main.adapter.ProjectListPagerAdapter;
import com.github.cyc.wanandroid.module.main.viewmodel.ProjectViewModel;

/**
 * 项目tab
 */
public class ProjectFragment extends BaseFragment<FragmentProjectBinding, ProjectViewModel>
        implements ScrollToTop {

    private ProjectListPagerAdapter mPagerAdapter;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ProjectViewModel(Injection.provideDataManager());
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initView();
        mViewModel.loadData();
    }

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    private void initView() {
        mPagerAdapter = new ProjectListPagerAdapter(getChildFragmentManager());
        mDataBinding.vpViewPager.setAdapter(mPagerAdapter);
        mDataBinding.tlTabLayout.setupWithViewPager(mDataBinding.vpViewPager);
    }

    @Override
    public void scrollToTop() {
        Fragment fragment = mPagerAdapter.getItem(mDataBinding.vpViewPager.getCurrentItem());
        if (fragment instanceof ScrollToTop) {
            ((ScrollToTop) fragment).scrollToTop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPagerAdapter.release();
    }
}
