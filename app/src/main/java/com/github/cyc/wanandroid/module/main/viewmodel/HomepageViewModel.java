package com.github.cyc.wanandroid.module.main.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel;
import com.github.cyc.wanandroid.data.DataManager;
import com.github.cyc.wanandroid.enums.LoadState;
import com.github.cyc.wanandroid.enums.RefreshState;
import com.github.cyc.wanandroid.http.HttpCode;
import com.github.cyc.wanandroid.http.base.BaseObserver;
import com.github.cyc.wanandroid.http.model.Article;
import com.github.cyc.wanandroid.http.model.ArticleList;
import com.github.cyc.wanandroid.http.model.Response;
import com.github.cyc.wanandroid.utils.RxUtils;
import com.github.cyc.wanandroid.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

/**
 * 首页tab的ViewModel
 */
public class HomepageViewModel extends BaseViewModel {

    public final ObservableList<Object> dataList = new ObservableArrayList<>();

    private DataManager mDataManager;

    private boolean mRefresh;

    private int mPageNum;

    public HomepageViewModel(@NonNull DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void loadData() {
        mRefresh = false;
        getAllData();
    }

    @Override
    public void reloadData() {
        loadData();
    }

    public void refreshData() {
        mRefresh = true;
        getAllData();
    }

    public void loadMoreData() {
        mPageNum++;
        getMoreArticleListData();
    }

    private void getAllData() {
        if (!mRefresh) {
            loadState.set(LoadState.LOADING);
        }

        addDisposable(Observable.zip(mDataManager.getTopArticleList(), mDataManager.getArticleList(0),
                new BiFunction<Response<List<Article>>, Response<ArticleList>, Response<ArticleList>>() {

                    @Override
                    public Response<ArticleList> apply(Response<List<Article>> topListResponse, Response<ArticleList> articleListResponse) throws Exception {
                        if (articleListResponse.getErrorCode() != HttpCode.SUCCESS) {
                            return articleListResponse;
                        }

                        ArticleList articleList = articleListResponse.getData();
                        List<Article> topList = topListResponse.getData();

                        if (articleList != null && articleList.getDatas() != null && !Utils.isListEmpty(topList)) {
                            for (Article article : topList) {
                                article.setTop(true);
                            }

                            articleList.getDatas().addAll(0, topList);
                        }

                        return articleListResponse;
                    }
                })
                .compose(RxUtils.applySchedulers())
                .subscribeWith(new BaseObserver<ArticleList>(loadState, !mRefresh) {

                    @Override
                    public void onNextX(ArticleList articleList) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END);
                        }

                        if (articleList != null && !Utils.isListEmpty(articleList.getDatas())) {
                            dataList.clear();
                            dataList.addAll(articleList.getDatas());

                            mPageNum = 0;

                            if (articleList.getCurPage() >= articleList.getPageCount()) {
                                setHasMore(false);
                            } else {
                                setHasMore(true);
                            }

                            if (!mRefresh) {
                                loadState.set(LoadState.SUCCESS);
                            }
                        } else {
                            if (!mRefresh) {
                                loadState.set(LoadState.NO_DATA);
                            }
                        }
                    }

                    @Override
                    public void onErrorX(int errorCode, String errorMsg) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END);
                        }
                    }
                }));
    }

    private void getMoreArticleListData() {
        addDisposable(mDataManager.getArticleList(mPageNum)
                .compose(RxUtils.applySchedulers())
                .subscribeWith(new BaseObserver<ArticleList>() {

                    @Override
                    public void onNextX(ArticleList articleList) {
                        setRefreshState(RefreshState.LOAD_MORE_END);

                        if (articleList != null) {
                            List<Article> articles = articleList.getDatas();
                            if (!Utils.isListEmpty(articles)) {
                                dataList.addAll(articles);
                            }

                            if (articleList.getCurPage() >= articleList.getPageCount()) {
                                setHasMore(false);
                            }
                        }
                    }

                    @Override
                    public void onErrorX(int errorCode, String errorMsg) {
                        setRefreshState(RefreshState.LOAD_MORE_END);
                    }
                }));
    }
}
