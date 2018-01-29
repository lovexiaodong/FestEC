package com.newpos.latte.net.download;

import android.os.AsyncTask;

import com.newpos.latte.net.RestClient;
import com.newpos.latte.net.RestCreator;
import com.newpos.latte.net.callback.IError;
import com.newpos.latte.net.callback.IFailure;
import com.newpos.latte.net.callback.IRequest;
import com.newpos.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class DownloadHander {

    private final String URL;

    private static final WeakHashMap<String, Object> PARAM = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String DOWNLOADDIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHander(String URL,
                          IRequest REQUEST,
                          ISuccess SUCCESS,
                          IFailure FAILURE,
                          IError ERROR,
                          String DOWNLOADDIR,
                          String EXTENSION,
                          String NAME) {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
        this.DOWNLOADDIR = DOWNLOADDIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
    }

    public final void handDownload(){

        if(REQUEST != null){
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAM)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response != null && response.isSuccessful()){
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, DOWNLOADDIR, EXTENSION, responseBody, NAME);

                            if(task.isCancelled()){
                                if(REQUEST != null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else{
                            if(ERROR != null){
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(FAILURE != null){
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
