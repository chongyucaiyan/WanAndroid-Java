package com.github.cyc.wanandroid.http.api;

import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.http.model.ArticleList;
import com.github.cyc.wanandroid.http.model.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 网络请求描述接口
 */
public interface ApiService {

    /**
     * 获取首页置顶文章列表
     *
     * @return 置顶文章列表数据
     */
    @GET("article/top/json")
    Observable<Response<List<Article>>> getTopArticleList();

    /**
     * 获取首页文章列表
     *
     * @param pageNum 页数
     * @return 文章列表数据
     */
    @GET("article/list/{pageNum}/json")
    Observable<Response<ArticleList>> getArticleList(@Path("pageNum") int pageNum);
}
