package com.newpos.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MultipleViewHolder extends BaseViewHolder{
    public MultipleViewHolder(View view) {
        super(view);
    }


    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }
}
