package com.example.wangjun.demoapk.BroadcastsDemo;

/**
 * Created by wangjun on 2017/10/11.
 */
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;

//public class CLoginForceOfflineReceiver extends BroadcastReceiver {
//
//    @Override
//    public void onReceive(final Context context, Intent intent) {
//
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//        dialogBuilder.setTitle("Warning");
//        dialogBuilder.setMessage("You are forced to be offline. Please try to login again.");
//        // 不可取消
//        dialogBuilder.setCancelable(false);
//        dialogBuilder.setPositiveButton("OK",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        CLoginForceOffline_ActivityCollector.finishAll();
//                        Intent intent = new Intent(context, CLoginForceOfflineLoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                });
//        AlertDialog alertDialog = dialogBuilder.create();
//        // 需要设置AlertDialog的类型，保证在广播接收器中可以正常弹出
//        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        alertDialog.show();
//    }
//
//}

public class CLoginForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        // 先去开启权限
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(context)) {
                Intent intent1 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                context.startActivity(intent1);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("warning");
        builder.setMessage("you are forced to be offline, please try to login again.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CLoginForceOffline_ActivityCollector.finishAll();
                Intent intent1 = new Intent(context, CLoginForceOfflineLoginActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}