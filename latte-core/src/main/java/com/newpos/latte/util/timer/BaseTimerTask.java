package com.newpos.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by Administrator on 2018/3/4 0004.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListerner listerner;

    public BaseTimerTask(ITimerListerner listerner) {
        this.listerner = listerner;
    }

    @Override
    public void run() {

        if(listerner != null){
            listerner.onTimer();
        }
    }
}
