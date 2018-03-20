package com.newpos.latte.app;

import android.content.Context;

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

    public static Context getApplicationContext(){
        return (Context) getConfigration().get(ConfigType.APPLICAION_CONTEXT.name());
    }
}
