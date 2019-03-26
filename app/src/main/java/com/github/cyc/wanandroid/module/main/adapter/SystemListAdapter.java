package com.github.cyc.wanandroid.module.main.adapter;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.base.adapter.BaseAdapter;
import com.github.cyc.wanandroid.http.model.Chapter;
import com.github.cyc.wanandroid.module.main.viewholder.SystemViewHolder;

/**
 * 体系列表Adapter
 */
public class SystemListAdapter extends BaseAdapter<SystemViewHolder> {

    public SystemListAdapter(@NonNull ObservableList<Object> dataList) {
        super(dataList);
    }

    @NonNull
    @Override
    public SystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemViewHolder holder, int position) {
        Object data = mDataList.get(position);
        holder.getViewModel().setBaseModel((Chapter) data);
    }
}
