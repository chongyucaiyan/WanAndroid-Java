package com.github.cyc.wanandroid.module.splash.viewmodel;

import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.app.Constant;
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel;
import com.github.cyc.wanandroid.navigator.MainNavigator;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 闪屏页的ViewModel
 */
public class SplashViewModel extends BaseViewModel {

    private MainNavigator mMainNavigator;

    public SplashViewModel(@NonNull MainNavigator mainNavigator) {
        mMainNavigator = mainNavigator;
    }

    public void startTimer() {
        addDisposable(Observable.timer(Constant.SPLASH_TIME, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {

                    @Override
                    public void accept(Long aLong) throws Exception {
                        mMainNavigator.startMainActivity();
                    }
                }));
    }
}
