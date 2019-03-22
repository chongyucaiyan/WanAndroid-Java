package com.github.cyc.wanandroid.data.source;

import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.http.api.ApiService;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.http.model.ArticleList;
import com.github.cyc.wanandroid.http.model.Banner;
import com.github.cyc.wanandroid.http.model.Chapter;
import com.github.cyc.wanandroid.http.model.Navigation;
import com.github.cyc.wanandroid.http.model.Response;

import java.util.List;

import io.reactivex.Observable;

/**
 * 网络数据源的实现
 */
public class HttpDataSource implements IHttpDataSource {

    private static volatile HttpDataSource sHttpDataSource;

    private ApiService mApiService;

    private HttpDataSource(@NonNull ApiService apiService) {
        mApiService = apiService;
    }

    public static HttpDataSource getInstance(ApiService apiService) {
        if (sHttpDataSource == null) {
            synchronized (HttpDataSource.class) {
                if (sHttpDataSource == null) {
                    sHttpDataSource = new HttpDataSource(apiService);
                }
            }
        }

        return sHttpDataSource;
    }

    @Override
    public Observable<Response<List<Banner>>> getBannerData() {
        return mApiService.getBannerData();
    }

    @Override
    public Observable<Response<List<Article>>> getTopArticleListData() {
        return mApiService.getTopArticleListData();
    }

    @Override
    public Observable<Response<ArticleList>> getArticleListData(int pageNum) {
        return mApiService.getArticleListData(pageNum);
    }

    @Override
    public Observable<Response<List<Chapter>>> getSystemListData() {
        return mApiService.getSystemListData();
    }

    @Override
    public Observable<Response<List<Chapter>>> getWeChatListData() {
        return mApiService.getWeChatListData();
    }

    @Override
    public Observable<Response<ArticleList>> getWeChatArticleListData(int id, int pageNum) {
        return mApiService.getWeChatArticleListData(id, pageNum);
    }

    @Override
    public Observable<Response<List<Navigation>>> getNavigationListData() {
        return mApiService.getNavigationListData();
    }

    @Override
    public Observable<Response<List<Chapter>>> getProjectListData() {
        return mApiService.getProjectListData();
    }

    @Override
    public Observable<Response<ArticleList>> getProjectArticleListData(int id, int pageNum) {
        return mApiService.getWeChatArticleListData(id, pageNum);
    }
}
