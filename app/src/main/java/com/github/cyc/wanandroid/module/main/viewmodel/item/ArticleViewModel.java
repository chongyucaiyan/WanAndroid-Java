package com.github.cyc.wanandroid.module.main.viewmodel.item;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.http.model.Tag;
import com.github.cyc.wanandroid.utils.ResourceUtils;
import com.github.cyc.wanandroid.utils.Utils;

import java.util.List;

/**
 * 文章的ViewModel
 */
public class ArticleViewModel extends BaseItemViewModel<Article> {

    public final ObservableField<String> tag = new ObservableField<>();

    public final ObservableField<String> author = new ObservableField<>();

    public final ObservableField<String> time = new ObservableField<>();

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> chapterName = new ObservableField<>();

    public final ObservableBoolean top = new ObservableBoolean(false);

    public final ObservableBoolean fresh = new ObservableBoolean(false);

    public ArticleViewModel() {

    }

    @Override
    protected void setAllModel(@NonNull Article article) {
        author.set(article.getAuthor());
        time.set(article.getNiceDate());
        title.set(article.getTitle());
        chapterName.set(String.format(ResourceUtils.getString(R.string.chapter_name_format), article.getSuperChapterName(), article.getChapterName()));

        List<Tag> tagList = article.getTags();
        if (!Utils.isListEmpty(tagList)) {
            tag.set(tagList.get(0).getName());
        } else {
            tag.set("");
        }

        Boolean top = article.isTop();
        if (top != null) {
            this.top.set(top);
        } else {
            this.top.set(false);
        }

        Boolean fresh = article.isFresh();
        if (fresh != null) {
            this.fresh.set(fresh);
        } else {
            this.fresh.set(false);
        }
    }
}
