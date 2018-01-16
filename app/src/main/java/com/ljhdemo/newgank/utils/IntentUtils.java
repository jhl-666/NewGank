package com.ljhdemo.newgank.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.kogitune.activity_transition.ActivityTransitionLauncher;

/**
 * Created by ljh on 2018/1/10.
 */

public class IntentUtils {

    public static void startImagePagerActivity(Context context, Intent intent, View view) {
        ActivityTransitionLauncher.with((Activity) context).from(view).launch(intent);
        /*//android V4包的类,用于两个activity转场时的缩放效果实现
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        try {
            ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());//API16及以上
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.browser_enter_anim, 0);
        }*/
    }
}
