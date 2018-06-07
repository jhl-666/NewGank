package com.ljhdemo.newgank.molule_main.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ljhdemo.newgank.common.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by ljh on 2018/1/4.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mData;
    private String[] mTitles;

    public MainAdapter(FragmentManager fm, ArrayList<BaseFragment> data, String[] titles) {
        super(fm);
        mData = data;
        mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
