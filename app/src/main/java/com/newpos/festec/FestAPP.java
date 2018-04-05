package com.newpos.festec;

import android.app.Application;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.newpos.latt.eec.database.DataBaseManager;
import com.newpos.latt.eec.icon.FontECModel;
import com.newpos.latte.app.Latte;
import com.newpos.latte.net.interceptor.DebugInterceptor;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/11/5 0005.
 */

public class FestAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .withIcon(new FontAwesomeModule())

                .withIcon(new FontECModel())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withWeChatAppId("")
                .withWeChatSecret("")
                .configure();

        Logger.addLogAdapter(new AndroidLogAdapter());

        initStecho();
        DataBaseManager.getInstance().init(this);

    }


    private  void initStecho(){
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
