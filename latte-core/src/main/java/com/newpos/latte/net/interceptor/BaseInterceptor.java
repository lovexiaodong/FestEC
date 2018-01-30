package com.newpos.latte.net.interceptor;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class BaseInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }


    protected LinkedHashMap<String, String> getUrlParameter(Chain chain){
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for(int i = 0; i < size; i++){
            params.put(url.queryParameterName(i), url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameter(Chain chain, String key){
        return chain.request().url().queryParameter(key);
    }

    protected LinkedHashMap<String, String> getBodyParameter(Chain chain){
        final FormBody body = (FormBody) chain.request().body();
        int size = body.size();
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for(int i = 0; i < size; i++){
            params.put(body.name(i), body.value(i));
        }
        return params;
    }

    protected String getBodyParameter(Chain chain, String key){
        return getBodyParameter(chain).get(key);
    }
}
