package com.github.cyc.wanandroid.module.main.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Injection;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentNavigationBinding;
import com.github.cyc.wanandroid.module.main.adapter.NavigationListAdapter;
import com.github.cyc.wanandroid.module.main.viewmodel.NavigationViewModel;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * 导航tab
 */
public class NavigationFragment extends BaseFragment<FragmentNavigationBinding, NavigationViewModel> {

    private VerticalTabLayout.OnTabSelectedListener mTabSelectedListener;

    private RecyclerView.OnScrollListener mScrollListener;

    private LinearLayoutManager mLayoutManager;

    private boolean mNeedScroll = false;

    private int mToPosition = -1;

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new NavigationViewModel(Injection.provideDataManager());
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initTabLayout();
        initRecyclerView();
        mViewModel.loadData();
    }

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    private void initTabLayout() {
        mTabSelectedListener = new VerticalTabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabView tab, int position) {
                smoothScrollToPosition(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        };
        mDataBinding.vtlTabLayout.addOnTabSelectedListener(mTabSelectedListener);
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mScrollListener = new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (mNeedScroll) {
                        mNeedScroll = false;
                        smoothScrollToPosition(mToPosition);
                    } else {
                        setTabSelected();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

            }
        };

        mDataBinding.rvRecyclerView.addOnScrollListener(mScrollListener);
        mDataBinding.rvRecyclerView.setLayoutManager(mLayoutManager);
        mDataBinding.rvRecyclerView.setAdapter(new NavigationListAdapter(mViewModel.dataList));
    }

    private void smoothScrollToPosition(int position) {
        int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManager.findLastVisibleItemPosition();

        if (position < firstPosition) {
            mDataBinding.rvRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastPosition) {
            int movePosition = position - firstPosition;
            if (movePosition >= 0 && movePosition < mLayoutManager.getChildCount()) {
                int top = mLayoutManager.getChildAt(movePosition).getTop();
                mDataBinding.rvRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            mDataBinding.rvRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mNeedScroll = true;
        }
    }

    private void setTabSelected() {
        int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
        mDataBinding.vtlTabLayout.setTabSelected(firstPosition, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDataBinding.vtlTabLayout.removeOnTabSelectedListener(mTabSelectedListener);
        mDataBinding.rvRecyclerView.removeOnScrollListener(mScrollListener);
    }
}
