package com.github.cyc.wanandroid.module.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.base.adapter.BaseAdapter;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.module.main.viewholder.ArticleViewHolder;

/**
 * 文章列表Adapter
 */
public class ArticleListAdapter extends BaseAdapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ARTICLE = 1;

    public ArticleListAdapter() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ARTICLE:
                return new ArticleViewHolder(parent);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object data = mDataList.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_ARTICLE:
                ((ArticleViewHolder) holder).getViewModel().setBaseModel((Article) data);
                break;

            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object data = mDataList.get(position);
        if (data instanceof Article) {
            return VIEW_TYPE_ARTICLE;
        } else {
            return super.getItemViewType(position);
        }
    }
}
