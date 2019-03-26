package com.github.cyc.wanandroid.module.main.adapter;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.base.adapter.BaseAdapter;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.module.main.model.BannerData;
import com.github.cyc.wanandroid.module.main.viewholder.ArticleViewHolder;
import com.github.cyc.wanandroid.module.main.viewholder.BannerViewHolder;

/**
 * 文章列表Adapter
 */
public class ArticleListAdapter extends BaseAdapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ARTICLE = 1;
    private static final int VIEW_TYPE_BANNER = 2;

    public ArticleListAdapter(@NonNull ObservableList<Object> dataList) {
        super(dataList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ARTICLE:
                return new ArticleViewHolder(parent);

            case VIEW_TYPE_BANNER:
                return new BannerViewHolder(parent);

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

            case VIEW_TYPE_BANNER:
                ((BannerViewHolder) holder).getViewModel().setBaseModel((BannerData) data);
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
        } else if (data instanceof BannerData) {
            return VIEW_TYPE_BANNER;
        } else {
            return super.getItemViewType(position);
        }
    }
}
