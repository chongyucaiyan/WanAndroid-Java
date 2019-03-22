package com.github.cyc.wanandroid.module.main.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel;
import com.github.cyc.wanandroid.data.DataManager;
import com.github.cyc.wanandroid.enums.LoadState;
import com.github.cyc.wanandroid.http.base.BaseObserver;
import com.github.cyc.wanandroid.http.model.Navigation;
import com.github.cyc.wanandroid.utils.RxUtils;
import com.github.cyc.wanandroid.utils.Utils;

import java.util.List;

/**
 * 导航tab的ViewModel
 */
public class NavigationViewModel extends BaseViewModel {

    public final ObservableList<Object> dataList = new ObservableArrayList<>();

    public final ObservableList<String> titleList = new ObservableArrayList<>();

    private DataManager mDataManager;

    public NavigationViewModel(@NonNull DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void loadData() {
        loadState.set(LoadState.LOADING);
        addDisposable(mDataManager.getNavigationListData()
                .compose(RxUtils.applySchedulers())
                .subscribeWith(new BaseObserver<List<Navigation>>(loadState) {

                    @Override
                    public void onNextX(List<Navigation> navigationList) {
                        if (!Utils.isListEmpty(navigationList)) {
                            for (Navigation navigation : navigationList) {
                                titleList.add(navigation.getName());
                            }
                            dataList.addAll(navigationList);

                            loadState.set(LoadState.SUCCESS);
                        } else {
                            loadState.set(LoadState.NO_DATA);
                        }
                    }
                }));
    }

    @Override
    public void reloadData() {
        loadData();
    }
}
