package com.newpos.latte.wechat.template;

import com.newpos.latte.wechat.BaseWXEntryActivity;
import com.newpos.latte.wechat.LetteWeChat;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    public void onSignInSucess(String userInfo) {

        LetteWeChat.getInstance().getSignInCallBack().onSignInSucess(userInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }
}
