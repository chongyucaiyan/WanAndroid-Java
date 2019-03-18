package com.github.cyc.wanandroid.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.github.cyc.wanandroid.base.adapter.BaseAdapter;
import com.github.cyc.wanandroid.enums.RefreshState;

import java.util.List;

/**
 * 应用的BindingAdapter
 */
public final class WanBindingAdapter {

    private WanBindingAdapter() {

    }

    /**
     * 设置RecyclerView的数据列表
     *
     * @param recyclerView RecyclerView
     * @param dataList     数据列表
     */
    @BindingAdapter("app:dataList")
    public static void setDataList(RecyclerView recyclerView, List<Object> dataList) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseAdapter) {
            BaseAdapter baseAdapter = (BaseAdapter) adapter;
            baseAdapter.setDataList(dataList);
        }
    }

    /**
     * 设置RefreshLayout的刷新状态
     *
     * @param refreshLayout RefreshLayout
     * @param refreshState  刷新状态
     */
    @BindingAdapter("app:refreshState")
    public static void setRefreshState(MaterialRefreshLayout refreshLayout, RefreshState refreshState) {
        if (refreshState == null) {
            return;
        }

        switch (refreshState) {
            case REFRESH_END:
                refreshLayout.finishRefresh();
                break;

            case LOAD_MORE_END:
                refreshLayout.finishRefreshLoadMore();
                break;

            default:
                break;
        }
    }

    /**
     * 设置RefreshLayout的加载更多
     *
     * @param refreshLayout RefreshLayout
     * @param hasMore       true表示还有更多，false表示没有更多了
     */
    @BindingAdapter("app:hasMore")
    public static void setHasMore(MaterialRefreshLayout refreshLayout, Boolean hasMore) {
        if (hasMore != null) {
            refreshLayout.setLoadMore(hasMore);
        }
    }
}
