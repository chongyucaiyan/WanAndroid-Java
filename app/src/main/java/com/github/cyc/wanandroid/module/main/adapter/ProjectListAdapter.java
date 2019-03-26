package com.github.cyc.wanandroid.module.main.adapter;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.base.adapter.BaseAdapter;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.module.main.viewholder.ProjectViewHolder;

/**
 * 项目列表Adapter
 */
public class ProjectListAdapter extends BaseAdapter<ProjectViewHolder> {

    public ProjectListAdapter(@NonNull ObservableList<Object> dataList) {
        super(dataList);
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProjectViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Object data = mDataList.get(position);
        holder.getViewModel().setBaseModel((Article) data);
    }
}
