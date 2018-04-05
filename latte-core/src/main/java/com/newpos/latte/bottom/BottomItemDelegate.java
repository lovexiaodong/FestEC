package com.newpos.latte.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.newpos.latte.delegates.LatterDelegate;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public abstract class BottomItemDelegate extends LatterDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    public static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();

        View view = getView();
        if(view != null){
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(this);
        }

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){

            if(System.currentTimeMillis() - mExitTime > EXIT_TIME){
                Toast.makeText(getContext(), "双击退出", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }else{
              _mActivity.finish();
              if(mExitTime != 0){
                  mExitTime = 0;
              }
            }
            return true;
        }

        return false;
    }
}
