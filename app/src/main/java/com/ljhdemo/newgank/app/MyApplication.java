package com.ljhdemo.newgank.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.ljh.baselibrary.utils.LogUtil;
import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.ui.CustomerView.x5webview.PreLoadX5Service;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * Created by ljh on 2017/12/29.
 */

public class MyApplication extends Application {
    private static Context mContext;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.theme_color, android.R.color.white);//全局设置主题颜色
                return new DeliveryHeader(context);
                //return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        initLogger();//初始化logger

        initTencentBrowser();
    }

    private void initLogger() {
        LogUtil.init();
    }

    private void initTencentBrowser() {
        Intent intent = new Intent(this, PreLoadX5Service.class);
        startService(intent);
    }


    public static Context getContext() {
        return mContext;
    }
}
