package com.newpos.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.newpos.latt.eec.luncher.LauncherDelegate;
import com.newpos.latt.eec.luncher.LauncherScrolledDelegate;
import com.newpos.latt.eec.main.ECBottomDelegate;
import com.newpos.latt.eec.sign.SignUpdelegate;
import com.newpos.latte.activitys.ProxyActivity;
import com.newpos.latte.app.ISignListener;
import com.newpos.latte.app.Latte;
import com.newpos.latte.delegates.LatterDelegate;
import com.newpos.latte.ui.launcher.IlauncherListener;
import com.newpos.latte.ui.launcher.LauncherListenerTag;

public class MainActivity extends ProxyActivity implements ISignListener, IlauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Latte.getConfigurator().withActivity(this);
    }

    @Override
    public LatterDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSucess() {
        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
        startWithPop(new ECBottomDelegate());
    }

    @Override
    public void onSignUpSucess() {
        Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void launcherFinished(LauncherListenerTag tag) {
        switch (tag){
            case SIGNEND:
                Toast.makeText(this, "已经登录了！", Toast.LENGTH_SHORT).show();
                startWithPop(new ECBottomDelegate());
                break;
            case NOT_SIGNED:
                startWithPop(new SignUpdelegate());
                break;
        }
    }
}
