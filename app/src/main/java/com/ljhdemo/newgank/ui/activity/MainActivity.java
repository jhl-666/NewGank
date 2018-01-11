package com.ljhdemo.newgank.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.jaeger.library.StatusBarUtil;
import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.ui.adapter.MainAdapter;
import com.ljhdemo.newgank.ui.base.BaseActivity;
import com.ljhdemo.newgank.ui.base.BaseFragment;
import com.ljhdemo.newgank.ui.fragment.WelfareFragment;

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
        data.add(WelfareFragment.newInstance());
        data.add(WelfareFragment.newInstance());

        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), data));
    }

    @Override
    protected void setBarColor() {
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.colorPrimaryDark), 112);
    }

    public void initToolBar(Toolbar toolbar, String title, int icon) {
        toolbar.setTitle(title);// 标题的文字需在setSupportActionBar之前，不然会无效
        toolbar.setNavigationIcon(icon);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setPopupTheme(R.style.AppTheme);
    }
}
