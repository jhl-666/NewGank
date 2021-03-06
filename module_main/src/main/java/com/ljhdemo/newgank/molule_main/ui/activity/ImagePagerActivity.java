package com.ljhdemo.newgank.molule_main.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.WindowManager;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;
import com.ljhdemo.newgank.common.base.BaseActivity;
import com.ljhdemo.newgank.common.widget.PhotoViewPager;
import com.ljhdemo.newgank.molule_main.R;
import com.ljhdemo.newgank.molule_main.R2;
import com.ljhdemo.newgank.molule_main.ui.adapter.ImagesPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class ImagePagerActivity extends BaseActivity {

    public static Intent newIntent(Context context, ArrayList<String> urls) {
        Intent intent = newIntent(context, urls, 0);
        return intent;
    }

    public static Intent newIntent(Context context, ArrayList<String> urls, int position) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra("urls", urls);
        intent.putExtra("position", position);
        return intent;
    }

    @BindView(R2.id.view_pager)
    PhotoViewPager mViewPager;
    @BindView(R2.id.pager_index_text_view)
    TextView mPagerIndexTextView;


    private ArrayList<String> urls;//图片地址数据
    private int position;//当前图片位置
    private ExitActivityTransition exitActivityTransition;
    private Bundle mSavedInstanceState;

    @Override
    protected void initVariable() {
        super.initVariable();
        urls = getIntent().getStringArrayListExtra("urls");

        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected int provideLayoutId(Bundle savedInstanceState) {
        mSavedInstanceState = savedInstanceState;
        return R.layout.activity_image_pager;
    }

    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

    private void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }


    @Override
    protected void setBarColor() {
        //StatusBarUtil.setColor(this, getResources().getColor(R.color.status_bar_black));
    }

    @Override
    protected void initView() {
         /* getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);*/

        //hideStatusBar();
        ImagesPagerAdapter imagesPagerAdapter = new ImagesPagerAdapter(this, urls);
        mViewPager.setAdapter(imagesPagerAdapter);
        mViewPager.setCurrentItem(position, false);
        mPagerIndexTextView.setText(String.valueOf((position + 1) + "/" + urls.size()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPagerIndexTextView.setText(String.valueOf((position + 1) + "/" + urls.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        exitActivityTransition = ActivityTransition.with(getIntent())
                .duration(500)
                .interpolator(new LinearOutSlowInInterpolator())
                .to(mViewPager)
                .start(mSavedInstanceState);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //exitActivityTransition.exit(this);
        exitActivityTransition.interpolator(new FastOutSlowInInterpolator()).exit(this);
    }
}
