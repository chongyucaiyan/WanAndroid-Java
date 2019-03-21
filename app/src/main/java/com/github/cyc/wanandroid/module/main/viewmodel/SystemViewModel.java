package com.github.cyc.wanandroid.module.main.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel;
import com.github.cyc.wanandroid.data.DataManager;
import com.github.cyc.wanandroid.enums.LoadState;
import com.github.cyc.wanandroid.enums.RefreshState;
import com.github.cyc.wanandroid.http.base.BaseObserver;
import com.github.cyc.wanandroid.http.model.System;
import com.github.cyc.wanandroid.utils.RxUtils;
import com.github.cyc.wanandroid.utils.Utils;

import java.util.List;

/**
 * 体系tab的ViewModel
 */
public class SystemViewModel extends BaseViewModel {

    public final ObservableList<Object> dataList = new ObservableArrayList<>();

    private DataManager mDataManager;

    private boolean mRefresh;

    public SystemViewModel(@NonNull DataManager dataManager) {
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

    private void getAllData() {
        if (!mRefresh) {
            loadState.set(LoadState.LOADING);
        }

        addDisposable(mDataManager.getSystemListData()
                .compose(RxUtils.applySchedulers())
                .subscribeWith(new BaseObserver<List<System>>(loadState, !mRefresh) {

                    @Override
                    public void onNextX(List<System> systemList) {
                        if (mRefresh) {
                            setRefreshState(RefreshState.REFRESH_END);
                        }

                        if (!Utils.isListEmpty(systemList)) {
                            dataList.clear();
                            dataList.addAll(systemList);

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
}
