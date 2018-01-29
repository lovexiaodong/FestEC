package com.newpos.latte.net;

import android.content.Context;

import com.newpos.latte.net.callback.IError;
import com.newpos.latte.net.callback.IFailure;
import com.newpos.latte.net.callback.IRequest;
import com.newpos.latte.net.callback.ISuccess;
import com.newpos.latte.net.callback.RequestCallback;
import com.newpos.latte.net.download.DownloadHander;
import com.newpos.latte.ui.LatteLoader;
import com.newpos.latte.ui.LoaderCreator;
import com.newpos.latte.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;
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

    private final File FILE;

    private final String DOWNLOADDIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(String url,
                      Map<String, Object> para,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError ERROR,
                      RequestBody body,
                      File file,
                      String downloadDir,
                      String name,
                      String extension,
                      LoaderStyle style,
                      Context context) {
        this.URL = url;
        this.PARAM.putAll(para);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = ERROR;
        this.BODY = body;
        this.FILE = file;
        this.DOWNLOADDIR = downloadDir;
        this.NAME = name;
        this.EXTENSION = extension;
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
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case POST:
                call = service.post(URL, PARAM);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAM);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final  MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
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
        if(BODY == null){
            request(HttpMethod.POST);
        }else {
            if(!PARAM.isEmpty()){
                throw new RuntimeException("param must to bt null");
            }
            request(HttpMethod.POST_RAW);
        }

    }
    public final void put(){

        if(BODY == null){
            request(HttpMethod.PUT);
        }else {
            if(!PARAM.isEmpty()){
                throw new RuntimeException("param must to bt null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
    public final void upload(){
        request(HttpMethod.UPLOAD);
    }


    public final void download(){

        new DownloadHander(URL, REQUEST, SUCCESS, FAILURE, ERROR, DOWNLOADDIR, EXTENSION, NAME).handDownload();
    }
}
