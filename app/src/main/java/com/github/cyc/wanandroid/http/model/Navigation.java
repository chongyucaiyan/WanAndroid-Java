package com.github.cyc.wanandroid.http.model;

import com.github.cyc.wanandroid.app.NoProguard;

import java.util.List;

public class Navigation implements NoProguard {

    private Integer cid;
    private String name;
    private List<Article> articles;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
