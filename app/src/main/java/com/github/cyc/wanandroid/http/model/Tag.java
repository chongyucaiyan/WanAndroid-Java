package com.github.cyc.wanandroid.http.model;

import com.github.cyc.wanandroid.app.NoProguard;

public class Tag implements NoProguard {

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
