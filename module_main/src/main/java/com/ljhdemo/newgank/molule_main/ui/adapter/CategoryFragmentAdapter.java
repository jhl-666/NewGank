package com.ljhdemo.newgank.molule_main.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljhdemo.newgank.common.image.ImageLoader;
import com.ljhdemo.newgank.molule_main.R;
import com.ljhdemo.newgank.molule_main.bean.GankResult;

import java.util.List;

/**
 * Created by ljh on 2018/1/18.
 */

public class CategoryFragmentAdapter extends BaseQuickAdapter<GankResult.ResultsBean, BaseViewHolder> {
    private Context mContext;

    public CategoryFragmentAdapter(@Nullable List<GankResult.ResultsBean> data, Context context) {
        super(R.layout.fragment_category_item, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GankResult.ResultsBean item) {
        helper.setText(R.id.title_text_view, item.getDesc());
        helper.setText(R.id.time_text_view, item.getPublishedAt().substring(0, item.getPublishedAt().indexOf("T")));
        helper.setText(R.id.source_text_view, item.getWho());

        ImageView imageView = (ImageView) helper.getView(R.id.image_view);

        if (item.getImages() == null || item.getImages().size() <= 0) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            ImageLoader.loadImageView(item.getImages().get(0),imageView);
        }
    }
}
