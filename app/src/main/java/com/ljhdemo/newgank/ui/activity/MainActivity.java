package com.ljhdemo.newgank.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.jaeger.library.StatusBarUtil;
import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.constant.Constants;
import com.ljhdemo.newgank.ui.adapter.MainAdapter;
import com.ljhdemo.newgank.ui.base.BaseActivity;
import com.ljhdemo.newgank.ui.base.BaseFragment;
import com.ljhdemo.newgank.ui.fragment.CategoryFragment;
import com.ljhdemo.newgank.ui.fragment.WelfareFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.right_btn)
    Button mRightBtn;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.smart_tab_layout)
    SmartTabLayout mSmartTabLayout;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();

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
    }

    @Override
    protected void setBarColor() {
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.status_bar), 112);
    }
}
