package com.ljhdemo.newgank.ui.base;


import android.content.Context;

import com.trello.navi2.component.support.NaviFragment;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * Created by ljh on 2017/12/13.
 */

abstract public class BaseFragment extends NaviFragment {

    protected Context mContext;
    protected final LifecycleProvider<FragmentEvent> provider
            = NaviLifecycle.createFragmentLifecycleProvider(this);//用于解决RxJava内存泄漏
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
