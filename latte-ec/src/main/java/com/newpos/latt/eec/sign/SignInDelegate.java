package com.newpos.latt.eec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.Patterns;
import android.view.View;

import com.newpos.latt.eec.R;
import com.newpos.latt.eec.R2;
import com.newpos.latte.delegates.LatterDelegate;
import com.newpos.latte.util.log.LatteLogger;

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

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){

        if(checkForm()){

            LatteLogger.d("tag","登录");
        }
    }

    @OnClick(R2.id.tv_sign_up)
    void onClickSignUp(){
        start(new SignUpdelegate());
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
}
