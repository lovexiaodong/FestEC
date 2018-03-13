package com.newpos.festec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
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
                .configure();

        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
