package com.newpos.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.newpos.latte.app.Latte;
import com.newpos.latte.net.callback.IRequest;
import com.newpos.latte.net.callback.ISuccess;
import com.newpos.latte.util.FileUtil;
import com.newpos.latte.util.StringUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private IRequest REQUEST;
    private ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success){
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... objects) {

        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        ResponseBody body = (ResponseBody) objects[2];
        String name = (String) objects[3];

        InputStream is = body.byteStream();
        if(StringUtil.isEmpty(downloadDir)){
            downloadDir = "down_loads";
        }

        if(StringUtil.isEmpty(extension)){
            extension = "";
        }

        if(StringUtil.isEmpty(name)){
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        }else{
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }


    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if(SUCCESS != null){
            SUCCESS.onSuccess(file.getPath());
        }

        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }

        autoInstall(file);
    }


    private void autoInstall(File file){
        if(FileUtil.getExtension(file.getPath()).equals("apk")){
            Intent install = new Intent();
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
