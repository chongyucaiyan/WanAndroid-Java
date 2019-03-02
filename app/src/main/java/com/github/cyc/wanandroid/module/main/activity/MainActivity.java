package com.github.cyc.wanandroid.module.main.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MenuItem;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.base.activity.BaseActivity;
import com.github.cyc.wanandroid.databinding.ActivityMainBinding;
import com.github.cyc.wanandroid.module.main.fragment.HomepageFragment;
import com.github.cyc.wanandroid.module.main.fragment.NavigationFragment;
import com.github.cyc.wanandroid.module.main.fragment.ProjectFragment;
import com.github.cyc.wanandroid.module.main.fragment.SystemFragment;
import com.github.cyc.wanandroid.module.main.fragment.WeChatFragment;
import com.github.cyc.wanandroid.module.main.viewmodel.MainViewModel;

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
        switchFragment(INDEX_HOMEPAGE);
    }

    private void initToolbar() {
        setSupportActionBar(mDataBinding.tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initBottomNavigationView() {
        mDataBinding.bnvBottomNav.setLabelVisibilityMode(1);
        mDataBinding.bnvBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_homepage:
                        switchFragment(INDEX_HOMEPAGE);
                        return true;

                    case R.id.tab_system:
                        switchFragment(INDEX_SYSTEM);
                        return true;

                    case R.id.tab_we_chat:
                        switchFragment(INDEX_WE_CHAT);
                        return true;

                    case R.id.tab_navigation:
                        switchFragment(INDEX_NAVIGATION);
                        return true;

                    case R.id.tab_project:
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
                        mDataBinding.dlDrawer.closeDrawers();
                        return true;

                    default:
                        return false;
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
}
