package com.newpos.latte.app;

import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2017/11/5 0005.
 */

public class Configurator {

    private static final HashMap<String, Object> LATTER_CONFIGRATOR =  new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator(){
        LATTER_CONFIGRATOR.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance(){
        return  Holder.INSTANCE;
    }
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    private void initIcons(){
        if(ICONS.size() > 0){
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i = 1; i < ICONS.size(); i++){
                initializer.with(ICONS.get(i));
            }
        }
    }
    public static HashMap<String, Object> getLatterConfigs(){
        return LATTER_CONFIGRATOR;
    }
    public final void configure(){
        initIcons();
        LATTER_CONFIGRATOR.put(ConfigType.CONFIG_READY.name(), true);
    }


    public Configurator withApiHost(String host){
        LATTER_CONFIGRATOR.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTER_CONFIGRATOR.put(ConfigType.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }
    public Configurator withWeChatAppId(String appId){
        LATTER_CONFIGRATOR.put(ConfigType.WE_CHAT_APP_ID.name(), appId);
        return this;
    }

    public Configurator withWeChatSecret(String secret){
        LATTER_CONFIGRATOR.put(ConfigType.WE_CHAT_APP_SECRET.name(), secret);
        return this;
    }
    public Configurator withActivity(Activity activity){
        LATTER_CONFIGRATOR.put(ConfigType.ACTIVITY.name(), activity);
        return this;
    }

    public Configurator withInterceptor(List<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTER_CONFIGRATOR.put(ConfigType.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }
    public Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTER_CONFIGRATOR.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Config is not ready, call config!");
        }
    }

    final <T>  T getConfigration(Enum<ConfigType> type){
        checkConfiguration();
        return (T) LATTER_CONFIGRATOR.get(type.name());
    }
}
