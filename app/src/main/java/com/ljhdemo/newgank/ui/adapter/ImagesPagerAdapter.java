package com.ljhdemo.newgank.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ljhdemo.newgank.GlideApp;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

public class ImagesPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<String> mUrls;

    public ImagesPagerAdapter(Context context, ArrayList<String> urls) {
        mContext = context;
        mUrls = urls;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //View view = (View) LayoutInflater.from(mContext).inflate(R.layout.activity_image_pager_item, null);
        PhotoView photoView = new PhotoView(mContext);
        photoView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        photoView.setAdjustViewBounds(true);
        GlideApp.with(mContext)
                .load(mUrls.get(position))
                .thumbnail(0.2f)
                .centerCrop()
                //.transition(withCrossFade(2000))//动画
                .into(photoView);
       /* photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductInfoImagesActivity.this);
                builder.setCancelable(true)
                        .setMessage("保存图片？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialogInterface, int i) {
                                RxPermissions rxPermissions = new RxPermissions(ProductInfoImagesActivity.this);
                                rxPermissions
                                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        .subscribe(new Action1<Boolean>() {
                                            @Override
                                            public void call(Boolean aBoolean) {
                                                if (aBoolean) {
                                                    SDFileHelper helper = new SDFileHelper(getApplicationContext());
                                                    helper.savePicture("aizhong" + System.currentTimeMillis() + ".jpg", uri);
                                                    dialogInterface.dismiss();
                                                } else {
                                                    ToastUtils.showToast(ProductInfoImagesActivity.this, "请在权限设置中打开读写sd卡权限");
                                                }
                                            }
                                        });
                            }
                        }).show();
                return true;
            }
        });*/

        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}