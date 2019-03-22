package com.github.cyc.wanandroid.module.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.adapter.BaseTagAdapter;
import com.github.cyc.wanandroid.http.model.Article;
import com.zhy.view.flowlayout.FlowLayout;

/**
 * 导航TagAdapter
 */
public class NavigationTagAdapter extends BaseTagAdapter<Article> {

    @Override
    public View getView(FlowLayout parent, int position, Article article) {
        TextView tvTag = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_navigation_tag, parent, false);
        tvTag.setText(article.getTitle());

        return tvTag;
    }
}
