package com.newpos.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.newpos.latte.R;
import com.newpos.latte.util.DimenUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class LatteLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADER = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotateMultipleIndicator.name();
    private static void showLoading(Context context, String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        int width = DimenUtils.getScreenWidth();
        int height = DimenUtils.getScreenHeight();
        Window window = dialog.getWindow();
        if(window != null){
           WindowManager.LayoutParams params =  window.getAttributes();
           params.width = width / LOADER_SIZE_SCALE;
           params.height = height / LOADER_SIZE_SCALE;
           params.height =  params.height + height / LOADER_OFFSET_SCALE;
           params.gravity = Gravity.CENTER;
        }
        LOADER.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADER){
            if(dialog != null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
