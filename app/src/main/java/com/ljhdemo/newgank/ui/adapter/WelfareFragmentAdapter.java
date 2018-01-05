package com.ljhdemo.newgank.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.bean.GankResult;

import java.util.List;

/**
 * Created by ljh on 2018/1/4.
 */

public class WelfareFragmentAdapter extends BaseQuickAdapter<GankResult.ResultsBean, BaseViewHolder> {

    public WelfareFragmentAdapter(int layoutResId, @Nullable List<GankResult.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankResult.ResultsBean item) {
        helper.setText(R.id.text_view, item.getUrl());
    }
}
