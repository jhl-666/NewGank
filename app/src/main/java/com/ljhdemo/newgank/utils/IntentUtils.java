package com.ljhdemo.newgank.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.ljhdemo.newgank.R;

/**
 * Created by ljh on 2018/1/10.
 */

public class IntentUtils {

    public static void startImagePagerActivity(Context context, Intent intent, View view,String transitionName) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context, view, transitionName).toBundle());
        } else {
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.browser_enter_anim, 0);
         /*   //android V4包的类,用于两个activity转场时的缩放效果实现
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            try {
                ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.browser_enter_anim, 0);
            }*/
        }
    }
}
