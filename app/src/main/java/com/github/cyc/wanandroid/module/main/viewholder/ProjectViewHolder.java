package com.github.cyc.wanandroid.module.main.viewholder;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder;
import com.github.cyc.wanandroid.databinding.ItemProjectBinding;
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity;
import com.github.cyc.wanandroid.module.main.viewmodel.item.ProjectViewModel;
import com.github.cyc.wanandroid.navigator.DetailsNavigator;

/**
 * 项目的ViewHolder
 */
public class ProjectViewHolder extends BaseViewHolder<ItemProjectBinding, ProjectViewModel>
        implements DetailsNavigator {

    public ProjectViewHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_project);
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ProjectViewModel(this);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }

    @Override
    public void startDetailsActivity(String url) {
        DetailsActivity.start(itemView.getContext(), url);
    }
}
