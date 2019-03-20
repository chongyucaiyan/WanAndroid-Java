package com.github.cyc.wanandroid.module.main.model;

import com.github.cyc.wanandroid.http.model.ArticleList;

public class HomepageData {

    private BannerData bannerData;
    private ArticleList articleList;

    public BannerData getBannerData() {
        return bannerData;
    }

    public void setBannerData(BannerData bannerData) {
        this.bannerData = bannerData;
    }

    public ArticleList getArticleList() {
        return articleList;
    }

    public void setArticleList(ArticleList articleList) {
        this.articleList = articleList;
    }
}
