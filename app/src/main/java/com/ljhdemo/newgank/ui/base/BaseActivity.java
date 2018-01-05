package com.ljhdemo.newgank.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.ljhdemo.newgank.R;
import com.ljhdemo.newgank.utils.BarUtils;
import com.ljhdemo.newgank.utils.NetworkUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public abstract class BaseActivity extends AppCompatActivity {
    // 可以把常量单独放到一个Class中
    public static final String ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";

    public boolean dataLoaded = false;
    protected AlertDialog mDialog = null;

    public BaseActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBase();

        initVariable();

        setContentView(savedInstanceState);

        initView();

        setBarColor();

       /* boolean networkEnable = NetworkUtils.isNetworkEnable(this.getApplicationContext());
        if (!networkEnable) {
            networkAlert(this);
            return;
        } else {
            initData();
        }*/
        initData();
    }

    // 可能全屏或者没有ActionBar等
    private void setBase() {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected void setBarColor() {
        int color = getResources().getColor(R.color.colorPrimaryDark);
        BarUtils.setColor(BaseActivity.this, color);
    }

    //得到上一个页面传递的数据
    protected void initVariable() {

    }

    //setContentView
    protected abstract void setContentView(Bundle savedInstanceState);

    // 抽象方法，初始化UI
    protected void initView() {
    }

    //初始化数据
    protected void initData() {
        dataLoaded = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 你可以添加多个Action捕获
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_NETWORK_CHANGE);
        registerReceiver(receiver, filter);

        Glide.with(this.getApplicationContext()).resumeRequests();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);

        Glide.with(this.getApplicationContext()).pauseRequests();
    }

    public void networkAlert(final Context context) {
        if (mDialog == null) {
            mDialog = new AlertDialog.Builder(context)
                    .setTitle("当前无网络")
                    .setCancelable(false)
                    .setMessage("请检查当前网络设置。")
                    .setNegativeButton("知道了", null)
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // 跳转到系统的网络设置界面
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            context.startActivity(intent);
                        }
                    }).show();
        } else if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 处理各种情况
            String action = intent.getAction();
            if (ACTION_NETWORK_CHANGE.equals(action)) { // 网络发生变化
                Observable.create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        SystemClock.sleep(2000);//收到广播后延迟两秒
                        subscriber.onNext(new Object());
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                // 处理网络问题
                                boolean networkEnable = NetworkUtils.hasNetWorkConection(BaseActivity.this);
                                if (!networkEnable) {
                                    networkAlert(BaseActivity.this);
                                } else if (!dataLoaded) {
                                    initData();
                                }
                            }
                        });
            }
        }
    };

    @Override
    protected void onDestroy() {
        Glide.with(this.getApplicationContext()).onDestroy();
        super.onDestroy();
    }
}
