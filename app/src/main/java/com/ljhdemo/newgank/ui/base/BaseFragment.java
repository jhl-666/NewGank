package com.ljhdemo.newgank.ui.base;


import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by ljh on 2017/12/13.
 */

abstract public class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
