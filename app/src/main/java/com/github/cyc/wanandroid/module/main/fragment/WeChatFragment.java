package com.github.cyc.wanandroid.module.main.fragment;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Injection;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentWeChatBinding;
import com.github.cyc.wanandroid.module.main.adapter.ArticleListPagerAdapter;
import com.github.cyc.wanandroid.module.main.viewmodel.WeChatViewModel;

/**
 * 公众号tab
 */
public class WeChatFragment extends BaseFragment<FragmentWeChatBinding, WeChatViewModel> {

    private ArticleListPagerAdapter mPagerAdapter;

    public static WeChatFragment newInstance() {
        return new WeChatFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_we_chat;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new WeChatViewModel(Injection.provideDataManager());
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initView();
        mViewModel.loadData();
    }

    @Override
    protected boolean isSupportLoad() {
        return true;
    }

    private void initView() {
        mPagerAdapter = new ArticleListPagerAdapter(getFragmentManager());
        mDataBinding.vpViewPager.setAdapter(mPagerAdapter);
        mDataBinding.tlTabLayout.setupWithViewPager(mDataBinding.vpViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPagerAdapter.release();
    }
}
