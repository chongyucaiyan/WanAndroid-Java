package com.github.cyc.wanandroid.module.main.viewmodel.item;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.navigator.DetailsNavigator;

/**
 * 项目的ViewModel
 */
public class ProjectViewModel extends BaseItemViewModel<Article> {

    public final ObservableField<String> imageUrl = new ObservableField<>();

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> desc = new ObservableField<>();

    public final ObservableField<String> time = new ObservableField<>();

    public final ObservableField<String> author = new ObservableField<>();

    private DetailsNavigator mDetailsNavigator;

    public ProjectViewModel(@NonNull DetailsNavigator detailsNavigator) {
        mDetailsNavigator = detailsNavigator;
    }

    @Override
    protected void setAllModel(@NonNull Article article) {
        imageUrl.set(article.getEnvelopePic());
        title.set(article.getTitle());
        desc.set(article.getDesc());
        time.set(article.getNiceDate());
        author.set(article.getAuthor());
    }

    public void onClickItem() {
        Article article = getBaseModel();
        if (article != null && !TextUtils.isEmpty(article.getLink())) {
            mDetailsNavigator.startDetailsActivity(article.getLink());
        }
    }
}
