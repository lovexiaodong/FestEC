package com.newpos.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.newpos.latt.eec.luncher.LauncherDelegate;
import com.newpos.latt.eec.luncher.LauncherScrolledDelegate;
import com.newpos.latte.activitys.ProxyActivity;
import com.newpos.latte.delegates.LatterDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public LatterDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
