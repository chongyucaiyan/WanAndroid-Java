package com.github.cyc.wanandroid.module.main.viewholder;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder;
import com.github.cyc.wanandroid.databinding.ItemArticleBinding;
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity;
import com.github.cyc.wanandroid.module.main.viewmodel.item.ArticleViewModel;
import com.github.cyc.wanandroid.navigator.DetailsNavigator;

/**
 * 文章的ViewHolder
 */
public class ArticleViewHolder extends BaseViewHolder<ItemArticleBinding, ArticleViewModel>
        implements DetailsNavigator {

    public ArticleViewHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_article);
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ArticleViewModel(this);
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
