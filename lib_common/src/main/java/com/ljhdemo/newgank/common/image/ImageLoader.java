package com.ljhdemo.newgank.common.image;

import android.widget.ImageView;

import com.ljhdemo.newgank.common.GlideApp;


/**
 * Created by ljh on 2018/6/4.
 */

public class ImageLoader {
    /**
     * 默认加载
     */
    public static void loadImageView(String path, ImageView mImageView) {
        GlideApp.with(mImageView.getContext()).load(path).into(mImageView);
    }

    /**
     * 先加载小图
     */
    public static void loadImageViewCenterCrop(String path, ImageView mImageView) {
        GlideApp.with(mImageView.getContext())
                .load(path)
                .centerCrop()
                .into(mImageView);
    }

    /**
     * 先加载小图
     */
    public static void loadImageViewThumbnail(String path, float thumbnail, ImageView mImageView) {
        GlideApp.with(mImageView.getContext())
                .load(path)
                .thumbnail(thumbnail)
                .centerCrop()
                .into(mImageView);
    }
}
