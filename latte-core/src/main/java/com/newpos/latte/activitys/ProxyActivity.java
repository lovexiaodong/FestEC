package com.newpos.latte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.newpos.latte.R;
import com.newpos.latte.delegates.LatterDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public abstract class ProxyActivity extends SupportActivity{

    public abstract LatterDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);

        if(savedInstanceState  == null){
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.gc();
        System.runFinalization();
    }
}
