package com.newpos.latte.wechat;

import android.app.Activity;

import com.newpos.latte.app.ConfigType;
import com.newpos.latte.app.Latte;
import com.newpos.latte.wechat.callbacks.IWeChatSignInCallBacks;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.security.Security;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class LetteWeChat {

    public static String APP_ID = Latte.getConfigration(ConfigType.WE_CHAT_APP_ID);

    public static String APP_SECRET = Latte.getConfigration(ConfigType.WE_CHAT_APP_SECRET);

    private final IWXAPI WXAPI;

    private IWeChatSignInCallBacks mSignInCallBack;

    private static final class Holder{
        private static final LetteWeChat INSTANCE = new LetteWeChat();
    }

    public static LetteWeChat getInstance(){
        return Holder.INSTANCE;
    }


    private LetteWeChat(){
        final Activity activity = Latte.getConfigration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_SECRET);
    }

    public IWXAPI getWXAPI(){
        return WXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "random_state";
            WXAPI.sendReq(req);
    }

    public LetteWeChat onSignInSucess(IWeChatSignInCallBacks mSignInCallBack) {
        this.mSignInCallBack = mSignInCallBack;
        return this;
    }

    public IWeChatSignInCallBacks getSignInCallBack() {
        return mSignInCallBack;
    }
}
