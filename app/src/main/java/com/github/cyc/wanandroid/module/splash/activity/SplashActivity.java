package com.github.cyc.wanandroid.module.splash.activity;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.activity.BaseActivity;
import com.github.cyc.wanandroid.databinding.ActivitySplashBinding;
import com.github.cyc.wanandroid.module.main.activity.MainActivity;
import com.github.cyc.wanandroid.module.splash.viewmodel.SplashViewModel;
import com.github.cyc.wanandroid.navigator.MainNavigator;

/**
 * 闪屏页
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel>
        implements MainNavigator {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new SplashViewModel(this);
    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        mViewModel.startTimer();
    }

    @Override
    public void startMainActivity() {
        MainActivity.start(this);
        finish();
    }
}
