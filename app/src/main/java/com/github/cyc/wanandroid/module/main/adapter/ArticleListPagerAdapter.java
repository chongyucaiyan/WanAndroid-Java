package com.github.cyc.wanandroid.module.main.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.github.cyc.wanandroid.base.adapter.BasePagerAdapter;
import com.github.cyc.wanandroid.http.model.Chapter;
import com.github.cyc.wanandroid.module.main.fragment.ArticleListFragment;

/**
 * 文章列表PagerAdapter
 */
public class ArticleListPagerAdapter extends BasePagerAdapter<Chapter> {

    public ArticleListPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentMap.get(position);
        if (fragment == null) {
            Chapter chapter = mDataList.get(position);
            fragment = ArticleListFragment.newInstance(chapter.getId());
            mFragmentMap.put(position, fragment);
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Chapter chapter = mDataList.get(position);
        return chapter.getName();
    }
}
