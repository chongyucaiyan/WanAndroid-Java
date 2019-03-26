package com.github.cyc.wanandroid.module.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Constant;
import com.github.cyc.wanandroid.app.Injection;
import com.github.cyc.wanandroid.app.ScrollToTop;
import com.github.cyc.wanandroid.base.fragment.BaseLazyFragment;
import com.github.cyc.wanandroid.databinding.FragmentProjectListBinding;
import com.github.cyc.wanandroid.module.main.adapter.ProjectListAdapter;
import com.github.cyc.wanandroid.module.main.viewmodel.ProjectListViewModel;

/**
 * 项目列表页
 */
public class ProjectListFragment extends BaseLazyFragment<FragmentProjectListBinding, ProjectListViewModel>
        implements ScrollToTop {

    private int mId;

    public static ProjectListFragment newInstance(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.KEY_ID, id);

        ProjectListFragment fragment = new ProjectListFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected void handleArguments(Bundle args) {
        mId = args.getInt(Constant.KEY_ID, -1);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ProjectListViewModel(Injection.provideDataManager());
        mViewModel.setId(mId);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    protected void onLazyLoad() {
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
        mDataBinding.rvRecyclerView.setAdapter(new ProjectListAdapter(mViewModel.dataList));
        mDataBinding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void scrollToTop() {
        mDataBinding.rvRecyclerView.smoothScrollToPosition(0);
    }
}
