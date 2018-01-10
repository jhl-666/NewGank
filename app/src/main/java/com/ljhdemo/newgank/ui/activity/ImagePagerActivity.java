package com.ljhdemo.newgank.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.ui.CustomerView.PhotoViewPager;
import com.ljhdemo.newgank.ui.adapter.ImagesPagerAdapter;
import com.ljhdemo.newgank.ui.base.BaseActivity;
import com.ljhdemo.newgank.utils.BarUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @BindView(R.id.view_pager)
    PhotoViewPager mViewPager;
    @BindView(R.id.pager_index_text_view)
    TextView mPagerIndexTextView;


    private ArrayList<String> urls;//图片地址数据
    private int position;//当前图片位置

    @Override
    protected void initVariable() {
        super.initVariable();
        urls = getIntent().getStringArrayListExtra("urls");

        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_image_pager);
        BarUtils.setColor(this, android.R.color.black);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
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
    }
}
