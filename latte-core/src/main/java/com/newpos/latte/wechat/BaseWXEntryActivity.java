package com.newpos.latte.wechat;

import com.alibaba.fastjson.JSONObject;
import com.newpos.latte.net.RestClient;
import com.newpos.latte.net.callback.IError;
import com.newpos.latte.net.callback.ISuccess;
import com.newpos.latte.util.log.LatteLogger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    public abstract void onSignInSucess(String userInfo);


    //微信发送请求到第三方后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {

        final String code = ((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
        authUrl.append(LetteWeChat.APP_ID);
        authUrl.append("&secret=");
        authUrl.append(LetteWeChat.APP_SECRET);
        authUrl.append("&code=");
        authUrl.append(code);
        authUrl.append("&grant_type=authorization_code");

        LatteLogger.d("authUrl = " + authUrl.toString());
        getAuth(authUrl.toString());
    }
    private void getAuth(final String authUrl){
        RestClient.builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject auth = JSONObject.parseObject(response);
                        String access_token = auth.getString("access_token");
                        String openId = auth.getString("openid");

                        StringBuilder userInfo = new StringBuilder();
                        userInfo.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                        .append(access_token)
                        .append("&openid=")
                        .append(openId)
                         .append("$lang=")
                                .append("zh_CN");
                        getUserInfo(userInfo.toString());
                    }
                }).build().get();
    }

    private void getUserInfo(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        onSignInSucess(response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .build()
                .get();
    }
}
