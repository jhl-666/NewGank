package com.ljhdemo.newgank.molule_main.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jaeger.library.StatusBarUtil;
import com.ljhdemo.newgank.common.base.BaseActivity;
import com.ljhdemo.newgank.common.base.BaseFragment;
import com.ljhdemo.newgank.molule_main.R;
import com.ljhdemo.newgank.molule_main.R2;
import com.ljhdemo.newgank.molule_main.constant.Constants;
import com.ljhdemo.newgank.molule_main.ui.adapter.MainAdapter;
import com.ljhdemo.newgank.molule_main.ui.dialog.OpenNotificationDialog;
import com.ljhdemo.newgank.molule_main.ui.fragment.CategoryFragment;
import com.ljhdemo.newgank.molule_main.ui.fragment.WelfareFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R2.id.right_btn)
    Button mRightBtn;
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;

    @BindView(R2.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @BindView(R2.id.smart_tab_layout)
    SmartTabLayout mSmartTabLayout;

    @Override
    protected int provideLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar(mToolbar, "福利", R.drawable.gank_icon_menu_white);

        ArrayList<BaseFragment> data = new ArrayList<>();
        data.add(WelfareFragment.newInstance());
        data.add(CategoryFragment.newInstance(Constants.FlagAndroid));
        data.add(CategoryFragment.newInstance(Constants.FlagIOS));
        data.add(CategoryFragment.newInstance(Constants.FlagVideo));
        data.add(CategoryFragment.newInstance(Constants.FlagExpand));
        data.add(CategoryFragment.newInstance(Constants.FlagJS));
        data.add(CategoryFragment.newInstance(Constants.FlagAll));

        mViewPager.setOffscreenPageLimit(5);
        String[] titles = new String[]{
                Constants.FlagWelFare,
                Constants.FlagAndroid,
                Constants.FlagIOS,
                Constants.FlagVideo,
                Constants.FlagExpand,
                Constants.FlagJS,
                Constants.FlagAll};
        mViewPager.setPageMargin(20);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), data, titles));
        mSmartTabLayout.setViewPager(mViewPager);
        mSmartTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        OpenNotificationDialog.showDialog(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.ljhdemo.newgank.common.R.menu.menu_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            ARouter.getInstance().build("/test/OneMainActivity").navigation();

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void setBarColor() {
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.status_bar), 112);
    }
}
