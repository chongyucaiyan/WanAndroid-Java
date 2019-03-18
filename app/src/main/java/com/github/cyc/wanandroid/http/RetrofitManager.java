package com.github.cyc.wanandroid.http;

import com.github.cyc.wanandroid.BuildConfig;
import com.github.cyc.wanandroid.app.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit管理类
 */
public class RetrofitManager {

    private static class RetrofitInstance {
        private static Retrofit sRetrofit = buildRetrofit();
    }

    private RetrofitManager() {

    }

    public static Retrofit get() {
        return RetrofitInstance.sRetrofit;
    }

    private static Retrofit buildRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Constant.TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(Constant.TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(Constant.TIMEOUT_WRITE, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
