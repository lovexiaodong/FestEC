package com.newpos.latte.app;

import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/5 0005.
 */

public final class Latte {

    public static Configurator init(Context context){
        getConfigration().put(ConfigType.APPLICAION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigration(){
        return Configurator.getInstance().getLatterConfigs();
    }
    public static <T> T getConfigration(ConfigType type){
        return Configurator.getInstance().getConfigration(type);
    }

    public static Context getApplicationContext(){
        return (Context) getConfigration().get(ConfigType.APPLICAION_CONTEXT.name());
    }

    public static Handler getHandler(){
        return (Handler) getConfigration().get(ConfigType.HANDLER.name());
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }
}
