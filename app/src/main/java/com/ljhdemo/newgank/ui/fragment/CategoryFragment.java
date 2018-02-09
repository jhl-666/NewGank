package com.ljhdemo.newgank.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljh.baselibrary.utils.ToastUtils;
import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.bean.GankResult;
import com.ljhdemo.newgank.ui.CustomerView.x5webview.TencentBrowserActivity;
import com.ljhdemo.newgank.ui.adapter.CategoryFragmentAdapter;
import com.ljhdemo.newgank.ui.base.MVPBaseFragment;
import com.ljhdemo.newgank.ui.iView.ICategoryView;
import com.ljhdemo.newgank.ui.presenter.impl.CategoryPresenterImpl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 分类fragment("福利", "Android", "ios", "休息视频", "拓展资源", "前端", "all")
 */
public class CategoryFragment extends MVPBaseFragment<ICategoryView, CategoryPresenterImpl> implements ICategoryView {

    public static CategoryFragment newInstance(String category) {
        CategoryFragment welfareFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_KEY, category);
        welfareFragment.setArguments(bundle);
        return welfareFragment;
    }

    public static final String CATEGORY_KEY = "CategoryFragment.CATEGORY_KEY";

    private String mCategory;
    private CategoryFragmentAdapter mCategoryFragmentAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mCategory = bundle.getString(CATEGORY_KEY);

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        mPresenter.getNewDatas();
    }

    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getNewDatas();
            }
        });
        mSmartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.getMoreDatas();
            }
        });
        mSmartRefreshLayout.setEnableLoadmore(true);
        mCategoryFragmentAdapter = new CategoryFragmentAdapter(null, mContext);
        mCategoryFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GankResult.ResultsBean resultsBean = mCategoryFragmentAdapter.getData().get(position);
                String url = resultsBean.getUrl();
                Intent intent = TencentBrowserActivity.newIntent(mContext, url, resultsBean.getDesc());
                startActivity(intent);
                //startActivity(new Intent(mContext, Main2Activity.class));
            }
        });
        mRecyclerView.setAdapter(mCategoryFragmentAdapter);
    }

    @Override
    public void setCategoryList(List<GankResult.ResultsBean> categoryList) {
        mCategoryFragmentAdapter.addData(categoryList);
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
    protected CategoryPresenterImpl createPresenter() {
        return new CategoryPresenterImpl(provider, mCategory);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
