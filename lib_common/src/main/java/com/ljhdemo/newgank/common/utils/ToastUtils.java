package com.ljhdemo.newgank.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by ljh on 2016/8/23.
 */
public class ToastUtils {
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showToast(Context context, String s) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;
    }


    public static void showToast(Context context, int resId) {
        showToast(context, context.getApplicationContext().getString(resId));
    }
}
