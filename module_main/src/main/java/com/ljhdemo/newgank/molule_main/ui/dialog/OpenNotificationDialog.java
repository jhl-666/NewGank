package com.ljhdemo.newgank.molule_main.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.ljhdemo.newgank.common.base.BaseDialogFragment;
import com.ljhdemo.newgank.common.utils.DensityUtil;
import com.ljhdemo.newgank.molule_main.R;


/**
 * 打开推送通知提示
 *
 * Created by zhaozhe on 2017/7/24.
 */
public class OpenNotificationDialog extends BaseDialogFragment implements View.OnClickListener {

    public static void showDialog(Activity activity) {
        if (activity instanceof AppCompatActivity) {
            OpenNotificationDialog dialog = new OpenNotificationDialog();
            dialog.show(((AppCompatActivity) activity));
        }
    }

    @NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            params.width = (int) (DensityUtil.getWidth(getContext()) - DensityUtil.dip2px(getContext(),80));
            window.setAttributes(params);
            window.setBackgroundDrawableResource(R.color.transparent);
        }
    }

    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                       @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_open_notification, container, false);
        view.findViewById(R.id.action)
                .setOnClickListener(this);
        view.findViewById(R.id.close)
                .setOnClickListener(this);
        return view;
    }

    @Override public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.action) {//Tool.gotoAppDetailSettingIntent(getContext());

        } else {
        }
        dismissAllowingStateLoss();
    }
}
