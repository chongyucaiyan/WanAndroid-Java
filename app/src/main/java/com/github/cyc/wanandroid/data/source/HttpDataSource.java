package com.github.cyc.wanandroid.data.source;

import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.http.api.ApiService;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.http.model.ArticleList;
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
    public Observable<Response<List<Article>>> getTopArticleList() {
        return mApiService.getTopArticleList();
    }

    @Override
    public Observable<Response<ArticleList>> getArticleList(int pageNum) {
        return mApiService.getArticleList(pageNum);
    }
}
