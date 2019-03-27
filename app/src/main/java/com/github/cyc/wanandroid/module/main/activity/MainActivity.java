package com.github.cyc.wanandroid.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.app.Constant;
import com.github.cyc.wanandroid.app.ScrollToTop;
import com.github.cyc.wanandroid.base.activity.BaseActivity;
import com.github.cyc.wanandroid.databinding.ActivityMainBinding;
import com.github.cyc.wanandroid.module.details.activity.DetailsActivity;
import com.github.cyc.wanandroid.module.main.fragment.HomepageFragment;
import com.github.cyc.wanandroid.module.main.fragment.NavigationFragment;
import com.github.cyc.wanandroid.module.main.fragment.ProjectFragment;
import com.github.cyc.wanandroid.module.main.fragment.SystemFragment;
import com.github.cyc.wanandroid.module.main.fragment.WeChatFragment;
import com.github.cyc.wanandroid.module.main.viewmodel.MainViewModel;
import com.github.cyc.wanandroid.utils.ToastUtils;

/**
 * 主页
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private static final int INDEX_HOMEPAGE = 0;
    private static final int INDEX_SYSTEM = 1;
    private static final int INDEX_WE_CHAT = 2;
    private static final int INDEX_NAVIGATION = 3;
    private static final int INDEX_PROJECT = 4;

    private SparseArray<Fragment> mFragmentMap = new SparseArray<>();

    private int mLastIndex = -1;

    private long mExitTime;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewModel() {
        mViewModel = new MainViewModel();
    }

    @Override
    protected void bindViewModel() {
        mDataBinding.setViewModel(mViewModel);
    }

    @Override
    protected void init() {
        initToolbar();
        initBottomNavigationView();
        initDrawerNavigationView();
        initFloatingActionButton();
        switchFragment(INDEX_HOMEPAGE);
    }

    private void initToolbar() {
        setSupportActionBar(mDataBinding.iToolbar.tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.app_name);
    }

    private void initBottomNavigationView() {
        mDataBinding.bnvBottomNav.setLabelVisibilityMode(1);
        mDataBinding.bnvBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_homepage:
                        mDataBinding.iToolbar.tbToolbar.setTitle(R.string.app_name);
                        switchFragment(INDEX_HOMEPAGE);
                        return true;

                    case R.id.tab_system:
                        mDataBinding.iToolbar.tbToolbar.setTitle(R.string.system);
                        switchFragment(INDEX_SYSTEM);
                        return true;

                    case R.id.tab_we_chat:
                        mDataBinding.iToolbar.tbToolbar.setTitle(R.string.we_chat);
                        switchFragment(INDEX_WE_CHAT);
                        return true;

                    case R.id.tab_navigation:
                        mDataBinding.iToolbar.tbToolbar.setTitle(R.string.navigation);
                        switchFragment(INDEX_NAVIGATION);
                        return true;

                    case R.id.tab_project:
                        mDataBinding.iToolbar.tbToolbar.setTitle(R.string.project);
                        switchFragment(INDEX_PROJECT);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void initDrawerNavigationView() {
        mDataBinding.nvDrawerNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_settings:
                        mDataBinding.dlDrawer.closeDrawers();
                        return true;

                    case R.id.nav_about:
                        DetailsActivity.start(MainActivity.this, Constant.ABOUT_URL);
                        mDataBinding.dlDrawer.closeDrawers();
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void initFloatingActionButton() {
        mDataBinding.fabScrollToTop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragment = getFragment(mLastIndex);
                if (fragment instanceof ScrollToTop) {
                    ((ScrollToTop) fragment).scrollToTop();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDataBinding.dlDrawer.openDrawer(Gravity.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Fragment getFragment(int index) {
        Fragment fragment = mFragmentMap.get(index);
        if (fragment == null) {
            switch (index) {
                case INDEX_HOMEPAGE:
                    fragment = HomepageFragment.newInstance();
                    break;

                case INDEX_SYSTEM:
                    fragment = SystemFragment.newInstance();
                    break;

                case INDEX_WE_CHAT:
                    fragment = WeChatFragment.newInstance();
                    break;

                case INDEX_NAVIGATION:
                    fragment = NavigationFragment.newInstance();
                    break;

                case INDEX_PROJECT:
                    fragment = ProjectFragment.newInstance();
                    break;

                default:
                    break;
            }
            mFragmentMap.put(index, fragment);
        }

        return fragment;
    }

    private void switchFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (mLastIndex != -1) {
            transaction.hide(getFragment(mLastIndex));
        }
        mLastIndex = index;

        Fragment fragment = getFragment(index);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_fragment_container, fragment);
        }

        transaction.show(fragment).commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        if (curTime - mExitTime > Constant.EXIT_TIME) {
            ToastUtils.show(R.string.exit_tips);
            mExitTime = curTime;
        } else {
            super.onBackPressed();
        }
    }
}
