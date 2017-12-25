package com.newpos.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.newpos.latte.app.Latte;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class DimenUtils {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
}
