package com.newpos.latte.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.net.FileNameMap;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public final class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context){
        final  AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if(LOADING_MAP.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name){
        if(name == null || name.isEmpty()){
            return null;
        }

        StringBuilder builder = new StringBuilder();
        if(!name.contains(".")){
            final String defaultName = AVLoadingIndicatorView.class.getPackage().getName();
            builder.append(defaultName);
            builder.append(".indicators");
            builder.append(".");

        }
        builder.append(name);
        try {
            final Class<?> drawableClass = Class.forName(builder.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
