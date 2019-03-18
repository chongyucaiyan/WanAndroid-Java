package com.github.cyc.wanandroid.module.main.viewholder;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder;
import com.github.cyc.wanandroid.databinding.ItemArticleBinding;
import com.github.cyc.wanandroid.module.main.viewmodel.item.ArticleViewModel;

/**
 * 文章的ViewHolder
 */
public class ArticleViewHolder extends BaseViewHolder<ItemArticleBinding, ArticleViewModel> {

    public ArticleViewHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_article);
    }

    @Override
    protected void initViewModel() {
        mViewModel = new ArticleViewModel();
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }
}
