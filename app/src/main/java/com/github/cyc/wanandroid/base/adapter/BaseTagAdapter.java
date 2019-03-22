package com.github.cyc.wanandroid.base.adapter;

import com.zhy.view.flowlayout.TagAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * TagAdapter的基类
 *
 * @param <T> 数据类型
 */
public abstract class BaseTagAdapter<T> extends TagAdapter<T> {

    private List<T> mDataList;

    public BaseTagAdapter() {
        super(new ArrayList<>());
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * 设置数据列表
     *
     * @param dataList 数据列表
     */
    public void setDataList(List<T> dataList) {
        mDataList = dataList;
        notifyDataChanged();
    }
}
