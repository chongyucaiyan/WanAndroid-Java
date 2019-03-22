package com.github.cyc.wanandroid.module.main.viewholder;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder;
import com.github.cyc.wanandroid.databinding.ItemSystemBinding;
import com.github.cyc.wanandroid.http.model.Chapter;
import com.github.cyc.wanandroid.module.main.activity.SystemDetailsActivity;
import com.github.cyc.wanandroid.module.main.viewmodel.item.SystemViewModel;
import com.github.cyc.wanandroid.navigator.SystemDetailsNavigator;

/**
 * 体系的ViewHolder
 */
public class SystemViewHolder extends BaseViewHolder<ItemSystemBinding, SystemViewModel>
        implements SystemDetailsNavigator {

    public SystemViewHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_system);
    }

    @Override
    protected void initViewModel() {
        mViewModel = new SystemViewModel(this);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }

    @Override
    public void startSystemDetailsActivity(Chapter chapter) {
        SystemDetailsActivity.start(itemView.getContext(), chapter);
    }
}
