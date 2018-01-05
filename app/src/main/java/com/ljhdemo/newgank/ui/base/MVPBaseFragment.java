package com.ljhdemo.newgank.ui.base;

import android.os.Bundle;

/**
 * Created by ljh on 2017/3/10.
 */

public abstract class MVPBaseFragment<V, T extends BasePresenter<V>> extends BaseFragment {
    protected T mPresenter;//Presenter 对象

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mPresenter = createPresenter(); //创建Presenter

        mPresenter.attachView((V) this);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    protected abstract T createPresenter();
}
