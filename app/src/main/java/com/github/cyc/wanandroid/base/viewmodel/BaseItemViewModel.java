package com.github.cyc.wanandroid.base.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

/**
 * Item的ViewModel的基类
 *
 * @param <T> 基础model的类型
 */
public abstract class BaseItemViewModel<T> extends BaseObservable {

    public final ObservableField<T> baseModel = new ObservableField<>();

    /**
     * 设置基础model
     *
     * @param t 基础model
     */
    public void setBaseModel(T t) {
        baseModel.set(t);

        if (t != null) {
            setAllModel(t);
        }
    }

    /**
     * 设置所有的model。如果设置了基础model，那么会设置所有的model
     *
     * @param t 基础model
     */
    protected abstract void setAllModel(@NonNull T t);
}
