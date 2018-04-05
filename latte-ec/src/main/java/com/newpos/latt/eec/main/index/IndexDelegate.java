package com.newpos.latt.eec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.newpos.latt.eec.R;
import com.newpos.latte.bottom.BottomItemDelegate;

/**
 * Created by Administrator on 2018/4/5 0005.
 */

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
