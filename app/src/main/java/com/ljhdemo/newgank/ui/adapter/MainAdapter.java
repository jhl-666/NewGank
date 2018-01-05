package com.ljhdemo.newgank.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ljhdemo.newgank.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by ljh on 2018/1/4.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mData;

    public MainAdapter(FragmentManager fm, ArrayList<BaseFragment> data) {
        super(fm);
        mData = data;
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
