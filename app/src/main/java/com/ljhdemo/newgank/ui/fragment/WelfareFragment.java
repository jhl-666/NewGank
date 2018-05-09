package com.ljhdemo.newgank.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljh.baselibrary.utils.DensityUtil;
import com.ljh.baselibrary.utils.IntentUtils;
import com.ljh.baselibrary.utils.SpaceItemDecoration;
import com.ljh.baselibrary.utils.ToastUtils;
import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.bean.GankResult;
import com.ljhdemo.newgank.ui.activity.ImagePagerActivity;
import com.ljhdemo.newgank.ui.adapter.WelfareFragmentAdapter;
import com.ljhdemo.newgank.ui.base.MVPBaseFragment;
import com.ljhdemo.newgank.ui.iView.IWelfareView;
import com.ljhdemo.newgank.ui.presenter.impl.WelfarePresenterImpl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ljh on 2018/1/4.
 */

public class WelfareFragment extends MVPBaseFragment<IWelfareView, WelfarePresenterImpl> implements IWelfareView {

    public static WelfareFragment newInstance() {
        return new WelfareFragment();
    }

    private WelfareFragmentAdapter welfareFragmentAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    @Override
    protected void initVariable(Bundle savedInstanceState) {

    }

    @Override
    protected int provideLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_welfare_layout;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(mContext, 2), 2));
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getNewDatas();
                //refreshlayout.finishRefresh();//传入false表示刷新失败
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //refreshlayout.finishLoadmore();//传入false表示加载失败
                mPresenter.getMoreDatas();
            }
        });
        mSmartRefreshLayout.setEnableLoadmore(true);
        welfareFragmentAdapter = new WelfareFragmentAdapter(R.layout.fragment_welfare_item, null, mContext);
        welfareFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        welfareFragmentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.image_view:
                        List<GankResult.ResultsBean> data = welfareFragmentAdapter.getData();
                        ArrayList<String> imgs = new ArrayList<>();
                        for (GankResult.ResultsBean resultsBean : data) {
                            imgs.add(resultsBean.getUrl());
                        }
                        //查看大图
                        Intent intent = ImagePagerActivity.newIntent(mContext, imgs, position);
                        IntentUtils.startImagePagerActivity(mContext, intent, view);
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(welfareFragmentAdapter);
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setWelFareList(List<GankResult.ResultsBean> welFareList) {
        welfareFragmentAdapter.addData(welFareList);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mContext,msg);
    }

    @Override
    public void overRefresh(boolean flag) {
        mSmartRefreshLayout.finishRefresh(flag);
        mSmartRefreshLayout.finishLoadmore(flag);
    }


    @Override
    public void setLoadMoreEnabled(boolean flag) {
        mSmartRefreshLayout.setEnableLoadmore(flag);
    }

    @Override
    protected WelfarePresenterImpl createPresenter() {
        return new WelfarePresenterImpl(provider);
    }

}
