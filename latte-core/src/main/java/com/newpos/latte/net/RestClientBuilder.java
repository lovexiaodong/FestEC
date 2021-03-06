package com.newpos.latte.net;

import android.content.Context;

import com.newpos.latte.net.callback.IError;
import com.newpos.latte.net.callback.IFailure;
import com.newpos.latte.net.callback.IRequest;
import com.newpos.latte.net.callback.ISuccess;
import com.newpos.latte.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/16 0016.
 */

public class RestClientBuilder {
    private  String mUrl;

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private  IRequest mRequest;
    private  ISuccess mSuccess;
    private  IFailure mFailure;
    private  IError mError;
    private  RequestBody mBody;
    private Context mContext;
    private LoaderStyle mStyle;
    private File mFile;

    private String mDownloadDir = null;
    private String mExtention = null;
    private String mName = null;


    RestClientBuilder(){}

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> map){
        this.PARAMS.putAll(map);
        return this;
    }

    public final RestClientBuilder params(String key, String param){
        this.PARAMS.put(key, param);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder downloadDir(String downloadDir){
        this.mDownloadDir = downloadDir;
        return this;
    }

    public final RestClientBuilder extension(String extension){
        this.mExtention = extension;
        return this;
    }

    public final RestClientBuilder name(String name){
        this.mName = name;
        return this;
    }


    public final RestClientBuilder file(String  filePass){
        this.mFile = new File(filePass);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json:charset=utf-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure){
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError error){
        this.mError = error;
        return this;
    }


    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext = context;
        this.mStyle = style;
        return this;
    }
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mStyle =  LoaderStyle.BallPulseIndicator;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl, PARAMS, mRequest,mSuccess, mFailure, mError, mBody, mFile, mDownloadDir, mName, mExtention,mStyle, mContext);
    }
}
