package com.github.cyc.wanandroid.app;

import android.app.Application;
import android.support.annotation.Nullable;

import com.github.cyc.wanandroid.BuildConfig;
import com.github.cyc.wanandroid.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class WanApplication extends Application {

    private static WanApplication sWanApplication;

    private static RefWatcher sRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        sRefWatcher = LeakCanary.install(this);
        // Normal app init code...

        sWanApplication = this;

        initLogger();
    }

    public static WanApplication getInstance() {
        return sWanApplication;
    }

    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag(getString(R.string.app_name))
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {

            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
