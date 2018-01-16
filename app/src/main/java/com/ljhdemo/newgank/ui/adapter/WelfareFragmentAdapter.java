package com.ljhdemo.newgank.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljhdemo.newgank.GlideApp;
import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.bean.GankResult;
import com.ljhdemo.newgank.utils.DensityUtil;

import java.util.HashMap;
import java.util.List;


/**
 * Created by ljh on 2018/1/4.
 */

public class WelfareFragmentAdapter extends BaseQuickAdapter<GankResult.ResultsBean, BaseViewHolder> {

    private Context mContext;
    private int screenWidth;
    private HashMap<Integer, String> hashMap;

    public WelfareFragmentAdapter(int layoutResId, @Nullable List<GankResult.ResultsBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        screenWidth = (int) (DensityUtil.getWidth(mContext) / 2);
        hashMap = new HashMap<>();
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GankResult.ResultsBean item) {
        GlideApp.with(mContext)
                .load(item.getUrl())
                //.transition(withCrossFade(2000))//动画
                .into((ImageView) helper.getView(R.id.image_view));
    }
}
