package com.github.cyc.wanandroid.module.main.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder;
import com.github.cyc.wanandroid.databinding.ItemNavigationBinding;
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity;
import com.github.cyc.wanandroid.module.main.adapter.NavigationTagAdapter;
import com.github.cyc.wanandroid.module.main.viewmodel.item.NavigationViewModel;
import com.github.cyc.wanandroid.navigator.DetailsNavigator;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * 导航的ViewHolder
 */
public class NavigationViewHolder extends BaseViewHolder<ItemNavigationBinding, NavigationViewModel>
        implements DetailsNavigator {

    public NavigationViewHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_navigation);
    }

    @Override
    protected void initViewModel() {
        mViewModel = new NavigationViewModel(this);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        mDataBinding.tflFlowLayout.setAdapter(new NavigationTagAdapter());
        mDataBinding.tflFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mViewModel.onClickTag(position);
                return true;
            }
        });
    }

    @Override
    public void startDetailsActivity(String url) {
        DetailsActivity.start(itemView.getContext(), url);
    }
}
