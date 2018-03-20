package com.newpos.latt.eec.sign;

import android.accounts.AccountManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newpos.latt.eec.database.DataBaseManager;
import com.newpos.latt.eec.database.UserProfile;
import com.newpos.latte.app.ISignListener;


/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class SignHander {

    public static void onSignUp(String response, ISignListener listener){
        final JSONObject profileObject =JSON.parseObject(response).getJSONObject("data");
        /**
         *   private long userId = 0;
         private String name;
         private String avater;
         private String gender;
         private String address;
         */
        long userId = profileObject.getLong("userId");
        String name = profileObject.getString("name");
        String avater = profileObject.getString("avater");
        String gender = profileObject.getString("gender");
        String address = profileObject.getString("address");
        UserProfile  profile = new UserProfile(userId, name, avater, gender, address);


        //注册成功了，也就登录成功了
        com.newpos.latte.app.AccountManager.setSignState(true);
        listener.onSignUpSucess();
    }

    public static void onSignIn(String response, ISignListener listener){
        final JSONObject profileObject =JSON.parseObject(response).getJSONObject("data");
        /**
         *   private long userId = 0;
         private String name;
         private String avater;
         private String gender;
         private String address;
         */
        long userId = profileObject.getLong("userId");
        String name = profileObject.getString("name");
        String avater = profileObject.getString("avater");
        String gender = profileObject.getString("gender");
        String address = profileObject.getString("address");
        UserProfile  profile = new UserProfile(userId, name, avater, gender, address);


        //注册成功了，也就登录成功了
        com.newpos.latte.app.AccountManager.setSignState(true);
        listener.onSignInSucess();
    }
}
