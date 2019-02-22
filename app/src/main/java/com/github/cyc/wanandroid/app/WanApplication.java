package com.github.cyc.wanandroid.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class WanApplication extends Application {

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

    }

    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }
}
