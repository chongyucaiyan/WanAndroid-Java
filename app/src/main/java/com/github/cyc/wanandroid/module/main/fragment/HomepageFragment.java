package com.github.cyc.wanandroid.module.main.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Injection;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentHomepageBinding;
import com.github.cyc.wanandroid.module.main.adapter.ArticleListAdapter;
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
        mViewModel = new HomepageViewModel(Injection.provideDataManager());
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initRefreshLayout();
        initRecyclerView();
        mViewModel.loadData();
    }

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    private void initRefreshLayout() {
        mDataBinding.mrlRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {

            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mViewModel.refreshData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                mViewModel.loadMoreData();
            }
        });
    }

    private void initRecyclerView() {
        mDataBinding.rvRecyclerView.setAdapter(new ArticleListAdapter(mViewModel.dataList));
        mDataBinding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
