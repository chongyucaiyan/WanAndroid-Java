package com.github.cyc.wanandroid.module.main.fragment;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.fragment.BaseFragment;
import com.github.cyc.wanandroid.databinding.FragmentWeChatBinding;
import com.github.cyc.wanandroid.module.main.viewmodel.WeChatViewModel;

/**
 * 公众号tab
 */
public class WeChatFragment extends BaseFragment<FragmentWeChatBinding, WeChatViewModel> {

    public static WeChatFragment newInstance() {
        return new WeChatFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_we_chat;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new WeChatViewModel();
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {

    }
}
