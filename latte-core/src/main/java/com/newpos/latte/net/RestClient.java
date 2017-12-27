package com.newpos.latte.net;

import android.content.Context;

import com.newpos.latte.net.callback.IError;
import com.newpos.latte.net.callback.IFailure;
import com.newpos.latte.net.callback.IRequest;
import com.newpos.latte.net.callback.ISuccess;
import com.newpos.latte.net.callback.RequestCallback;
import com.newpos.latte.ui.LatteLoader;
import com.newpos.latte.ui.LoaderCreator;
import com.newpos.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/11/16 0016.
 */

public class RestClient {

    private final String URL;

    private static final WeakHashMap<String, Object> PARAM = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> para,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError ERROR,
                      RequestBody body,
                      LoaderStyle style,
                      Context context) {
        this.URL = url;
        this.PARAM.putAll(para);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = ERROR;
        this.BODY = body;
        this.LOADER_STYLE = style;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestfService service = RestCreator.getRestService();
        Call<String> call = null;
        if(REQUEST != null){
            REQUEST.onRequestStart();
        }

        if(LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch(method){
            case GET:
                call = service.get(URL, PARAM);
                break;
            case PUT:
                call = service.put(URL, PARAM);
                break;
            case POST:
                call = service.post(URL, PARAM);
                break;
            case DELETE:
                call = service.delete(URL, PARAM);
                break;
            default:
                break;
        }
        call.enqueue(getRequestCallBack());
    }

    private Callback<String> getRequestCallBack(){
        return new RequestCallback(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
