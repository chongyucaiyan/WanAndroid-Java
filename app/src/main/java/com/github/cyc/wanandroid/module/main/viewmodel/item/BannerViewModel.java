package com.github.cyc.wanandroid.module.main.viewmodel.item;

import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel;
import com.github.cyc.wanandroid.http.model.Banner;
import com.github.cyc.wanandroid.module.main.model.BannerData;
import com.github.cyc.wanandroid.navigator.DetailsNavigator;

/**
 * Banner的ViewModel
 */
public class BannerViewModel extends BaseItemViewModel<BannerData> {

    private DetailsNavigator mDetailsNavigator;

    public BannerViewModel(DetailsNavigator detailsNavigator) {
        mDetailsNavigator = detailsNavigator;
    }

    @Override
    protected void setAllModel(@NonNull BannerData bannerData) {

    }

    public void onClickBanner(int position) {
        if (mDetailsNavigator == null) {
            return;
        }

        BannerData bannerData = getBaseModel();
        if (bannerData != null && bannerData.getBannerList() != null
                && bannerData.getBannerList().size() > position) {
            Banner banner = bannerData.getBannerList().get(position);
            mDetailsNavigator.startDetailsActivity(banner.getUrl());
        }
    }
}
