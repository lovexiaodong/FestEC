package com.newpos.latte.app;

import com.newpos.latte.util.storage.LattePreference;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }

    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }


    public static void checkAccount(IUserChecker checker){
        if(isSignIn()){
            checker.onSignIn();
        }else{
            checker.onNoSignIn();
        }
    }
}
