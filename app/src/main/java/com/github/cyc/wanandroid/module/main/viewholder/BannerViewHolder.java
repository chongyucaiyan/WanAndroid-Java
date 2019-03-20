package com.github.cyc.wanandroid.module.main.viewholder;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.GlideImageLoader;
import com.github.cyc.wanandroid.base.viewholder.BaseViewHolder;
import com.github.cyc.wanandroid.databinding.ItemBannerBinding;
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity;
import com.github.cyc.wanandroid.module.main.viewmodel.item.BannerViewModel;
import com.github.cyc.wanandroid.navigator.DetailsNavigator;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

/**
 * Banner的ViewHolder
 */
public class BannerViewHolder extends BaseViewHolder<ItemBannerBinding, BannerViewModel>
        implements DetailsNavigator {

    public BannerViewHolder(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_banner);
    }

    @Override
    protected void initViewModel() {
        mViewModel = new BannerViewModel(this);
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        // 设置Banner样式
        mDataBinding.bBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        // 设置图片加载器
        mDataBinding.bBanner.setImageLoader(new GlideImageLoader());
        // 设置Banner动画效果
        mDataBinding.bBanner.setBannerAnimation(Transformer.Default);
        // 设置指示器位置（当Banner样式中有指示器时）
        mDataBinding.bBanner.setIndicatorGravity(BannerConfig.RIGHT);
        // 设置Banner监听器
        mDataBinding.bBanner.setOnBannerListener(new OnBannerListener() {

            @Override
            public void OnBannerClick(int position) {
                mViewModel.onClickBanner(position);
            }
        });
    }

    @Override
    public void startDetailsActivity(String url) {
        DetailsActivity.start(itemView.getContext(), url);
    }
}
