package com.newpos.festec;

import com.newpos.latte.activitys.ProxyActivity;
import com.newpos.latte.delegates.LatterDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatterDelegate setRootDelegate() {
        return new MainDelegate();
    }
}
