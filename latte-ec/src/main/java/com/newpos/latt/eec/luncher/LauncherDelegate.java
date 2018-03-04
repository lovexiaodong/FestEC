package com.newpos.latt.eec.luncher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.newpos.latt.eec.R;
import com.newpos.latt.eec.R2;
import com.newpos.latte.activitys.ProxyActivity;
import com.newpos.latte.delegates.LatterDelegate;
import com.newpos.latte.util.timer.BaseTimerTask;
import com.newpos.latte.util.timer.ITimerListerner;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/4 0004.
 */

public class LauncherDelegate extends LatterDelegate implements ITimerListerner{

    @BindView(R2.id.tv_lumcher_timer)
    TextView mTVTimer;

    @OnClick(R2.id.tv_lumcher_timer)
    void onClickLuncherTimer(){

    }

    private Timer mTimer = null;
    private int mCount = 5;

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask timer = new BaseTimerTask(this);
        mTimer.schedule(timer, 0 , 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_luncher;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
            initTimer();
    }

    @Override
    public void onTimer() {
        _mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTVTimer != null){
                    mTVTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if(mCount < 0){
                        if(mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                        }
                    }
                }

            }
        });
    }
}
