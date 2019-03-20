package com.github.cyc.wanandroid.module.details.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Constant;
import com.github.cyc.wanandroid.app.Injection;
import com.github.cyc.wanandroid.base.activity.BaseActivity;
import com.github.cyc.wanandroid.databinding.ActivityDetailsBinding;
import com.github.cyc.wanandroid.module.details.viewmodel.DetailsViewModel;
import com.just.agentweb.AgentWeb;

/**
 * 详情页
 */
public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> {

    private AgentWeb mAgentWeb;

    private String mUrl;

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(Constant.KEY_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void handleIntent(Intent intent) {
        mUrl = intent.getStringExtra(Constant.KEY_URL);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new DetailsViewModel(Injection.provideDataManager());
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initToolbar();
        initWebView();
    }

    private void initToolbar() {
        setSupportActionBar(mDataBinding.iToolbar.tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initWebView() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mDataBinding.llRoot, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .createAgentWeb()
                .ready()
                .go(mUrl);
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            mDataBinding.iToolbar.tbToolbar.setTitle(title);
        }
    };

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
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!mAgentWeb.back()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
