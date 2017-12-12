package com.newpos.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.newpos.latte.delegates.LatterDelegate;
import com.newpos.latte.net.RestClient;
import com.newpos.latte.net.callback.IError;
import com.newpos.latte.net.callback.IFailure;
import com.newpos.latte.net.callback.ISuccess;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class MainDelegate extends LatterDelegate {
    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();

    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://www.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .builder()
                .get();
    }
}
