package com.github.cyc.wanandroid.http.api;

import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.http.model.ArticleList;
import com.github.cyc.wanandroid.http.model.Banner;
import com.github.cyc.wanandroid.http.model.Chapter;
import com.github.cyc.wanandroid.http.model.Navigation;
import com.github.cyc.wanandroid.http.model.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求描述接口
 */
public interface ApiService {

    /**
     * 获取首页Banner数据
     *
     * @return Banner数据
     */
    @GET("banner/json")
    Observable<Response<List<Banner>>> getBannerData();

    /**
     * 获取首页置顶文章列表数据
     *
     * @return 置顶文章列表数据
     */
    @GET("article/top/json")
    Observable<Response<List<Article>>> getTopArticleListData();

    /**
     * 获取首页文章列表数据
     *
     * @param pageNum 页数
     * @return 文章列表数据
     */
    @GET("article/list/{pageNum}/json")
    Observable<Response<ArticleList>> getArticleListData(@Path("pageNum") int pageNum);

    /**
     * 获取体系列表数据
     *
     * @return 体系列表数据
     */
    @GET("tree/json")
    Observable<Response<List<Chapter>>> getSystemListData();

    /**
     * 获取公众号列表数据
     *
     * @return 公众号列表数据
     */
    @GET("wxarticle/chapters/json")
    Observable<Response<List<Chapter>>> getWeChatListData();

    /**
     * 获取某个公众号文章列表数据
     *
     * @param id      公众号ID
     * @param pageNum 页数
     * @return 公众号文章列表数据
     */
    @GET("wxarticle/list/{id}/{pageNum}/json")
    Observable<Response<ArticleList>> getWeChatArticleListData(@Path("id") int id, @Path("pageNum") int pageNum);

    /**
     * 获取导航列表数据
     *
     * @return 导航列表数据
     */
    @GET("navi/json")
    Observable<Response<List<Navigation>>> getNavigationListData();

    /**
     * 获取项目分类列表数据
     *
     * @return 项目分类列表数据
     */
    @GET("project/tree/json")
    Observable<Response<List<Chapter>>> getProjectListData();

    /**
     * 获取某个项目分类文章列表数据
     *
     * @param id      项目分类ID
     * @param pageNum 页数
     * @return 项目分类文章列表数据
     */
    @GET("project/list/{pageNum}/json")
    Observable<Response<ArticleList>> getProjectArticleListData(@Query("cid") int id, @Path("pageNum") int pageNum);

}
