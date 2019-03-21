package com.github.cyc.wanandroid.data;

import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.data.source.IHttpDataSource;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.http.model.ArticleList;
import com.github.cyc.wanandroid.http.model.Banner;
import com.github.cyc.wanandroid.http.model.Response;
import com.github.cyc.wanandroid.http.model.System;

import java.util.List;

import io.reactivex.Observable;

/**
 * 数据管理类
 */
public class DataManager implements IHttpDataSource {

    private static volatile DataManager sDataManager;

    private IHttpDataSource mHttpDataSource;

    private DataManager(@NonNull IHttpDataSource httpDataSource) {
        mHttpDataSource = httpDataSource;
    }

    public static DataManager getInstance(IHttpDataSource httpDataSource) {
        if (sDataManager == null) {
            synchronized (DataManager.class) {
                if (sDataManager == null) {
                    sDataManager = new DataManager(httpDataSource);
                }
            }
        }

        return sDataManager;
    }

    @Override
    public Observable<Response<List<Banner>>> getBannerData() {
        return mHttpDataSource.getBannerData();
    }

    @Override
    public Observable<Response<List<Article>>> getTopArticleListData() {
        return mHttpDataSource.getTopArticleListData();
    }

    @Override
    public Observable<Response<ArticleList>> getArticleListData(int pageNum) {
        return mHttpDataSource.getArticleListData(pageNum);
    }

    @Override
    public Observable<Response<List<System>>> getSystemListData() {
        return mHttpDataSource.getSystemListData();
    }
}
