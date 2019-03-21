package com.github.cyc.wanandroid.module.main.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Injection;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentSystemBinding;
import com.github.cyc.wanandroid.module.main.adapter.SystemListAdapter;
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
        mViewModel = new SystemViewModel(Injection.provideDataManager());
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
        });
    }

    private void initRecyclerView() {
        mDataBinding.rvRecyclerView.setAdapter(new SystemListAdapter());
        mDataBinding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
