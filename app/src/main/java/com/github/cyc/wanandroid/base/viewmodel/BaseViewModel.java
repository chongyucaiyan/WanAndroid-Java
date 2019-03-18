package com.github.cyc.wanandroid.base.viewmodel;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.BR;
import com.github.cyc.wanandroid.enums.LoadState;
import com.github.cyc.wanandroid.enums.RefreshState;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ViewModel的基类
 */
public abstract class BaseViewModel extends BaseObservable implements DefaultLifecycleObserver {

    public final ObservableField<LoadState> loadState = new ObservableField<>();

    // 因为设置相同的值也要通知改变，所以采用@Bindable的方式
    private RefreshState refreshState;

    private Boolean hasMore;

    private CompositeDisposable mCompositeDisposable;

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Logger.i(getClass().getSimpleName());
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Logger.i(getClass().getSimpleName());

        // 取消所有的订阅，避免内存泄露
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * 获取加载状态
     *
     * @return 加载状态
     */
    public LoadState getLoadState() {
        return loadState.get();
    }

    @Bindable
    public RefreshState getRefreshState() {
        return refreshState;
    }

    protected void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
        notifyPropertyChanged(BR.refreshState);
    }

    @Bindable
    public Boolean getHasMore() {
        return hasMore;
    }

    protected void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
        notifyPropertyChanged(BR.hasMore);
    }

    /**
     * 重新加载数据。没有网络，点击重试时回调
     */
    public void reloadData() {

    }

    /**
     * 添加订阅事件
     *
     * @param disposable 订阅事件
     */
    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
