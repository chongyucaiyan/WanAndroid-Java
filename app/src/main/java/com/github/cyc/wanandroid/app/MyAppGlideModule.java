package com.github.cyc.wanandroid.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * Glide模块。配置Glide
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {

    private static final long SIZE_MEMORY_CACHE = 1024 * 1024 * 20;
    private static final long SIZE_DISK_CACHE = 1024 * 1024 * 100;

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565))
                .setMemoryCache(new LruResourceCache(SIZE_MEMORY_CACHE))
                .setDiskCache(new InternalCacheDiskCacheFactory(context, SIZE_DISK_CACHE));
    }
}
