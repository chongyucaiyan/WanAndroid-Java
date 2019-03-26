package com.github.cyc.wanandroid.binding;

import android.databinding.BindingAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.cjj.MaterialRefreshLayout;
import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.GlideApp;
import com.github.cyc.wanandroid.base.adapter.BasePagerAdapter;
import com.github.cyc.wanandroid.base.adapter.BaseTagAdapter;
import com.github.cyc.wanandroid.enums.RefreshState;
import com.github.cyc.wanandroid.http.model.Banner;
import com.github.cyc.wanandroid.module.main.model.BannerData;
import com.github.cyc.wanandroid.utils.ResourceUtils;
import com.github.cyc.wanandroid.utils.Utils;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

/**
 * 应用的BindingAdapter
 */
public final class WanBindingAdapter {

    private WanBindingAdapter() {

    }

    /**
     * 设置ViewPager的数据列表
     *
     * @param viewPager ViewPager
     * @param dataList  数据列表
     * @param <T>       数据类型
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter("app:dataList")
    public static <T> void setDataList(ViewPager viewPager, List<T> dataList) {
        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter instanceof BasePagerAdapter) {
            BasePagerAdapter basePagerAdapter = (BasePagerAdapter) adapter;
            basePagerAdapter.setDataList(dataList);
        }
    }

    /**
     * 设置TagFlowLayout的数据列表
     *
     * @param tagFlowLayout TagFlowLayout
     * @param dataList      数据列表
     * @param <T>           数据类型
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter("app:dataList")
    public static <T> void setDataList(TagFlowLayout tagFlowLayout, List<T> dataList) {
        TagAdapter adapter = tagFlowLayout.getAdapter();
        if (adapter instanceof BaseTagAdapter) {
            BaseTagAdapter baseTagAdapter = (BaseTagAdapter) adapter;
            baseTagAdapter.setDataList(dataList);
        }
    }

    /**
     * 设置RefreshLayout的刷新状态
     *
     * @param refreshLayout RefreshLayout
     * @param refreshState  刷新状态
     */
    @BindingAdapter("app:refreshState")
    public static void setRefreshState(MaterialRefreshLayout refreshLayout, RefreshState refreshState) {
        if (refreshState == null) {
            return;
        }

        switch (refreshState) {
            case REFRESH_END:
                refreshLayout.finishRefresh();
                break;

            case LOAD_MORE_END:
                refreshLayout.finishRefreshLoadMore();
                break;

            default:
                break;
        }
    }

    /**
     * 设置RefreshLayout的加载更多
     *
     * @param refreshLayout RefreshLayout
     * @param hasMore       true表示还有更多，false表示没有更多了
     */
    @BindingAdapter("app:hasMore")
    public static void setHasMore(MaterialRefreshLayout refreshLayout, Boolean hasMore) {
        if (hasMore != null) {
            refreshLayout.setLoadMore(hasMore);
        }
    }

    /**
     * 设置ImageView的图片URL
     *
     * @param imageView ImageView
     * @param imageUrl  图片URL
     */
    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        GlideApp.with(imageView)
                .load(imageUrl)
                .placeholder(R.drawable.ic_timelapse)
                .error(R.drawable.ic_error)
                .into(imageView);
    }

    /**
     * 设置Banner的数据
     *
     * @param banner     Banner
     * @param bannerData Banner数据
     */
    @BindingAdapter("app:bannerData")
    public static void setBannerData(com.youth.banner.Banner banner, BannerData bannerData) {
        if (bannerData == null || Utils.isListEmpty(bannerData.getBannerList())) {
            return;
        }

        List<String> imageUrlList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        for (Banner data : bannerData.getBannerList()) {
            imageUrlList.add(data.getImagePath());
            titleList.add(data.getTitle());
        }

        banner.setImages(imageUrlList);
        banner.setBannerTitles(titleList);
        banner.start();
    }

    /**
     * 设置VerticalTabLayout的标题
     *
     * @param tabLayout VerticalTabLayout
     * @param titleList 标题列表
     */
    @BindingAdapter("app:titleList")
    public static void setTitleList(VerticalTabLayout tabLayout, List<String> titleList) {
        if (Utils.isListEmpty(titleList)) {
            return;
        }

        tabLayout.setTabAdapter(new SimpleTabAdapter() {

            @Override
            public int getCount() {
                return titleList.size();
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(titleList.get(position))
                        .setTextColor(ResourceUtils.getColor(R.color.primary_text),
                                ResourceUtils.getColor(R.color.secondary_text))
                        .setTextSize(16)
                        .build();
            }
        });
    }
}
