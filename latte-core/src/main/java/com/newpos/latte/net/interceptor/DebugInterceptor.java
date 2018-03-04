package com.newpos.latte.net.interceptor;

import android.support.annotation.RawRes;
import android.util.Log;

import com.newpos.latte.util.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/2/6 0006.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String DEBUG_URL, int DEBUG_RAWID) {
        this.DEBUG_URL = DEBUG_URL;
        this.DEBUG_RAW_ID = DEBUG_RAWID;
    }

    private Response getResponse(Chain chain, String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    public Response debugResponse(Chain chain, @RawRes int rawId){
        String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String url = chain.request().url().toString();

        Log.i("MainDelegate", "intercept: ");
        if(url.contains(DEBUG_URL)){
            return debugResponse(chain, DEBUG_RAW_ID);
        }else{
            return  chain.proceed(chain.request());
        }
    }
}
