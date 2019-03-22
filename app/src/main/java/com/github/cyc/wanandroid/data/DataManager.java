package com.github.cyc.wanandroid.data;

import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.data.source.IHttpDataSource;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.http.model.ArticleList;
import com.github.cyc.wanandroid.http.model.Banner;
import com.github.cyc.wanandroid.http.model.Chapter;
import com.github.cyc.wanandroid.http.model.Navigation;
import com.github.cyc.wanandroid.http.model.Response;

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
    public Observable<Response<List<Chapter>>> getSystemListData() {
        return mHttpDataSource.getSystemListData();
    }

    @Override
    public Observable<Response<List<Chapter>>> getWeChatListData() {
        return mHttpDataSource.getWeChatListData();
    }

    @Override
    public Observable<Response<ArticleList>> getWeChatArticleListData(int id, int pageNum) {
        return mHttpDataSource.getWeChatArticleListData(id, pageNum);
    }

    @Override
    public Observable<Response<List<Navigation>>> getNavigationListData() {
        return mHttpDataSource.getNavigationListData();
    }

    @Override
    public Observable<Response<List<Chapter>>> getProjectListData() {
        return mHttpDataSource.getProjectListData();
    }

    @Override
    public Observable<Response<ArticleList>> getProjectArticleListData(int id, int pageNum) {
        return mHttpDataSource.getWeChatArticleListData(id, pageNum);
    }
}
