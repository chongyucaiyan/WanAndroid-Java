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
import com.github.cyc.wanandroid.http.model.Banner;
import com.github.cyc.wanandroid.http.model.Response;
import com.github.cyc.wanandroid.module.main.model.BannerData;
import com.github.cyc.wanandroid.module.main.model.HomepageData;
import com.github.cyc.wanandroid.utils.RxUtils;
import com.github.cyc.wanandroid.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function3;

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

        Observable<Response<List<Banner>>> bannerObservable = mDataManager.getBannerData();
        Observable<Response<List<Article>>> topListObservable = mDataManager.getTopArticleListData();
        Observable<Response<ArticleList>> articleListObservable = mDataManager.getArticleListData(0);

        addDisposable(Observable.zip(bannerObservable, topListObservable, articleListObservable,
                new Function3<Response<List<Banner>>, Response<List<Article>>, Response<ArticleList>, Response<HomepageData>>() {

                    @Override
                    public Response<HomepageData> apply(Response<List<Banner>> bannerResponse,
                                                        Response<List<Article>> topListResponse,
                                                        Response<ArticleList> articleListResponse) throws Exception {
                        Response<HomepageData> response = new Response<>();
                        if (articleListResponse.getErrorCode() != HttpCode.SUCCESS) {
                            response.setErrorCode(articleListResponse.getErrorCode());
                            response.setErrorMsg(articleListResponse.getErrorMsg());
                            return response;
                        }

                        HomepageData homepageData = new HomepageData();
                        homepageData.setArticleList(articleListResponse.getData());

                        if (bannerResponse.getErrorCode() == HttpCode.SUCCESS) {
                            BannerData bannerData = new BannerData();
                            bannerData.setBannerList(bannerResponse.getData());
                            homepageData.setBannerData(bannerData);
                        }

                        if (topListResponse.getErrorCode() == HttpCode.SUCCESS) {
                            ArticleList articleList = articleListResponse.getData();
                            List<Article> topList = topListResponse.getData();

                            if (articleList != null && articleList.getDatas() != null && !Utils.isListEmpty(topList)) {
                                for (Article article : topList) {
                                    article.setTop(true);
                                }

                                articleList.getDatas().addAll(0, topList);
                            }
                        }

                        response.setErrorCode(HttpCode.SUCCESS);
                        response.setData(homepageData);

                        return response;
                    }
                })
                .compose(RxUtils.applySchedulers())
                .subscribeWith(new BaseObserver<HomepageData>(loadState, !mRefresh) {

                    @Override
                    public void onNextX(HomepageData homepageData) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END);
                        }

                        BannerData bannerData = homepageData.getBannerData();
                        ArticleList articleList = homepageData.getArticleList();

                        if (articleList != null && !Utils.isListEmpty(articleList.getDatas())) {
                            dataList.clear();
                            if (bannerData != null && !Utils.isListEmpty(bannerData.getBannerList())) {
                                dataList.add(bannerData);
                            }
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
        addDisposable(mDataManager.getArticleListData(mPageNum)
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
