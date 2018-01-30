package com.newpos.latte.net;

import com.newpos.latte.app.ConfigType;
import com.newpos.latte.app.Configurator;
import com.newpos.latte.app.Latte;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/11/16 0016.
 */

public class RestCreator {

    private static final class ParamHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }
    public static WeakHashMap<String, Object> getParams(){
        return ParamHolder.PARAMS;
    }

    public static RestfService getRestService(){
        return RestfServiceHolder.RESTF_SERVICE;
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigration().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;

        private static final  OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static final OkHttpClient.Builder addInterceptors(){
            List<Interceptor> interceptors = (List<Interceptor>) Configurator.getLatterConfigs().get(ConfigType.INTERCEPTOR);

            if(interceptors != null && !interceptors.isEmpty()){
                for (Interceptor interceptor:interceptors) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptors()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestfServiceHolder{
        private static final RestfService RESTF_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestfService.class);
    }
}
