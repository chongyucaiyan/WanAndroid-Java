package com.github.cyc.wanandroid.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Constant;
import com.github.cyc.wanandroid.app.ScrollToTop;
import com.github.cyc.wanandroid.base.activity.BaseActivity;
import com.github.cyc.wanandroid.base.viewmodel.BaseViewModel;
import com.github.cyc.wanandroid.databinding.ActivitySystemDetailsBinding;
import com.github.cyc.wanandroid.http.model.Chapter;
import com.github.cyc.wanandroid.module.main.adapter.ArticleListPagerAdapter;

/**
 * 体系详情页
 */
public class SystemDetailsActivity extends BaseActivity<ActivitySystemDetailsBinding, BaseViewModel> {

    private ArticleListPagerAdapter mPagerAdapter;

    private Chapter mChapter;

    public static void start(Context context, Chapter chapter) {
        Intent intent = new Intent(context, SystemDetailsActivity.class);
        intent.putExtra(Constant.KEY_CHAPTER, chapter);
        context.startActivity(intent);
    }

    @Override
    protected void handleIntent(Intent intent) {
        mChapter = (Chapter) intent.getSerializableExtra(Constant.KEY_CHAPTER);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_system_details;
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        initToolbar();
        initViewPager();
        initFloatingActionButton();
    }

    private void initToolbar() {
        setSupportActionBar(mDataBinding.tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(mChapter.getName());
    }

    private void initViewPager() {
        mPagerAdapter = new ArticleListPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setDataList(mChapter.getChildren());
        mDataBinding.vpViewPager.setAdapter(mPagerAdapter);
        mDataBinding.tlTabLayout.setupWithViewPager(mDataBinding.vpViewPager);
    }

    private void initFloatingActionButton() {
        mDataBinding.fabScrollToTop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                scrollToTop();
            }
        });
    }

    private void scrollToTop() {
        Fragment fragment = mPagerAdapter.getItem(mDataBinding.vpViewPager.getCurrentItem());
        if (fragment instanceof ScrollToTop) {
            ((ScrollToTop) fragment).scrollToTop();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPagerAdapter.release();
    }
}
