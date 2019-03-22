package com.github.cyc.wanandroid.module.main.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.base.adapter.BaseAdapter;
import com.github.cyc.wanandroid.http.model.Navigation;
import com.github.cyc.wanandroid.module.main.viewholder.NavigationViewHolder;

/**
 * 导航列表Adapter
 */
public class NavigationListAdapter extends BaseAdapter<NavigationViewHolder> {

    @NonNull
    @Override
    public NavigationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NavigationViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationViewHolder holder, int position) {
        Object data = mDataList.get(position);
        holder.getViewModel().setBaseModel((Navigation) data);
    }
}
