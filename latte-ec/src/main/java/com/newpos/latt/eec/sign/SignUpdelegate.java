package com.newpos.latt.eec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.newpos.latt.eec.R;
import com.newpos.latt.eec.R2;
import com.newpos.latte.delegates.LatterDelegate;
import com.newpos.latte.net.RestClient;
import com.newpos.latte.net.callback.ISuccess;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public class SignUpdelegate extends LatterDelegate {

    @BindView(R2.id.edit_sign_up_name)
    AppCompatEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    AppCompatEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    AppCompatEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    AppCompatEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    AppCompatEditText mRePassword = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if(checkForm()){
           /* RestClient.builder()
                    .url("sign_up")
                    .params("","")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .builder();*/

            Toast.makeText(_mActivity, "注册成功！", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.tv_sign_in)
    void onClickSignIn(){
        start(new SignInDelegate());
    }


    private boolean checkForm(){
        final String name  = mName.getText().toString();
        final String email  = mEmail.getText().toString();
        final String phone  = mPhone.getText().toString();
        final String password  = mPassword.getText().toString();
        final String rePassword  = mRePassword.getText().toString();

        boolean isPass = true;
        if(name.isEmpty()){
            mName.setError("请输入姓名");
            isPass = false;
        }else{
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请至少输入6位密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if(rePassword.isEmpty() || rePassword.length() < 6 || !rePassword.equals(password)){
            mRePassword.setError("密码错误");
            isPass = false;
        }else {
            mRePassword.setError(null);
        }

        return isPass;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
