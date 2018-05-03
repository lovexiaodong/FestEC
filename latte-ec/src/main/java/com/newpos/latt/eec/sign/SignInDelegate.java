package com.newpos.latt.eec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.newpos.latt.eec.R;
import com.newpos.latt.eec.R2;
import com.newpos.latte.app.ISignListener;
import com.newpos.latte.delegates.LatterDelegate;
import com.newpos.latte.net.RestClient;
import com.newpos.latte.net.callback.ISuccess;
import com.newpos.latte.util.log.LatteLogger;
import com.newpos.latte.wechat.LetteWeChat;
import com.newpos.latte.wechat.callbacks.IWeChatSignInCallBacks;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class SignInDelegate extends LatterDelegate {

    @BindView(R2.id.edit_sign_in_email)
    AppCompatEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    AppCompatEditText mPassword = null;


    private ISignListener mSignListener;
    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){

        if(checkForm()){

            RestClient.builder()
                    .params("email", mEmail.getText().toString())
                    .params("passworld", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .build()
                    .post();

            String respoent = "{" +
                    "  \"code\": 0," +
                    "  \"message\": \"OK\"," +
                    "  \"data\": { " +
                    "    \"userId\": 1, " +
                    "    \"name\": \"猿猿\", " +
                    "    \"avatar\": \"http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0\", " +
                    "    \"gender\": \"男\", " +
                    "    \"address\": \"西安\" " +
                    "  }}";
            LatteLogger.d("tag","登录");
            SignHander.onSignIn(respoent, mSignListener);
        }
    }

    @OnClick(R2.id.tv_sign_up)
    void onClickSignUp(){
        start(new SignUpdelegate());
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){
        LetteWeChat.getInstance().onSignInSucess(new IWeChatSignInCallBacks() {
            @Override
            public void onSignInSucess(String userInfo) {

            }
        }).signIn();
    }

    private boolean checkForm(){
        final String email  = mEmail.getText().toString();
        final String password  = mPassword.getText().toString();

        boolean isPass = true;
        if(email.isEmpty()){
            mEmail.setError("请输入姓名");
            isPass = false;
        }else{
            mEmail.setError(null);
        }


        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请至少输入6位密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof ISignListener){
            mSignListener = (ISignListener) activity;
        }
    }
}
