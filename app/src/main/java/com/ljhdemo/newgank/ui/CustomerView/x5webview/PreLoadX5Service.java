package com.ljhdemo.newgank.ui.CustomerView.x5webview;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tencent.smtt.sdk.QbSdk;

public class PreLoadX5Service extends Service {
    public PreLoadX5Service() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initX5();
        preinitX5WebCore();
    }

    private void initX5() {
        QbSdk.initX5Environment(getApplicationContext(), null);
    }

    private void preinitX5WebCore() {
        if (!QbSdk.isTbsCoreInited()) { // preinit只需要调用一次，如果已经完成了初始化，那么就直接构造view
            QbSdk.preInit(getApplicationContext(), null);// 设置X5初始化完成的回调接口 } }
        }
    }
}
