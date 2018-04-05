package com.newpos.latt.eec.main;

import android.graphics.Color;

import com.newpos.latt.eec.main.index.IndexDelegate;
import com.newpos.latte.bottom.BaseBottomDelegate;
import com.newpos.latte.bottom.BottomItemDelegate;
import com.newpos.latte.bottom.BottomTabBean;
import com.newpos.latte.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018/4/5 0005.
 */

public class ECBottomDelegate extends BaseBottomDelegate {


    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {

        final  LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
