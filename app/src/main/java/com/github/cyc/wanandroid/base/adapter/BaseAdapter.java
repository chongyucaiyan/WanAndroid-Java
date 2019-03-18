package com.github.cyc.wanandroid.base.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Adapter的基类
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<Object> mDataList;

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    /**
     * 设置数据列表
     *
     * @param dataList 数据列表
     */
    public void setDataList(List<Object> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }
}
