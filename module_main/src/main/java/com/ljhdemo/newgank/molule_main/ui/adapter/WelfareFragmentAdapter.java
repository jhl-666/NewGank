package com.ljhdemo.newgank.molule_main.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljhdemo.newgank.common.image.ImageLoader;
import com.ljhdemo.newgank.common.utils.DensityUtil;
import com.ljhdemo.newgank.molule_main.R;
import com.ljhdemo.newgank.molule_main.bean.GankResult;

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
        screenWidth = (int) (DensityUtil.getScreenWidth() / 2);
        hashMap = new HashMap<>();
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GankResult.ResultsBean item) {
        ImageLoader.loadImageViewCenterCrop(item.getUrl(),(ImageView) helper.getView(R.id.image_view));

        helper.addOnClickListener(R.id.image_view);
    }
}
