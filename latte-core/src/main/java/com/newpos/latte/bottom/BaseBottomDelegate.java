package com.newpos.latte.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.newpos.latte.R;
import com.newpos.latte.R2;
import com.newpos.latte.delegates.LatterDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public abstract class BaseBottomDelegate extends LatterDelegate implements View.OnClickListener{

    private final ArrayList<BottomItemDelegate> ITEMS_DELEGATESTES = new ArrayList<>();
    private final ArrayList<BottomTabBean> ITEMS_BEANS = new ArrayList<>();

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;

    private int mClickColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    @BindView(R2.id.bottom_bar_container)
    ContentFrameLayout mBottomContainer;

    @ColorInt
    public abstract int setClickColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndexDelegate = setIndexDelegate();

        if (setClickColor() != 0) {
            mClickColor = setClickColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();

        LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);

        ITEMS.putAll(items);


        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            ITEMS_BEANS.add(key);
            ITEMS_DELEGATESTES.add(value);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);

            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);

            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemText = (AppCompatTextView) item.getChildAt(1);

            BottomTabBean bean = ITEMS_BEANS.get(i);

            itemIcon.setText(bean.getIcon());
            itemText.setText(bean.getTitle());

            if(i == mIndexDelegate){
                itemText.setTextColor(mClickColor);
                itemIcon.setTextColor(mClickColor);
            }

        }

        SupportFragment[] fragment = ITEMS_DELEGATESTES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_container, mIndexDelegate, fragment);
    }

    private void resetColor(){
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++){
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemText = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemText.setTextColor(Color.GRAY);
        }
    }


    @Override
    public void onClick(View v) {
        resetColor();
        final int tag = (int) v.getTag();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemText = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickColor);
        itemText.setTextColor(mClickColor);

        showHideFragment(ITEMS_DELEGATESTES.get(tag), ITEMS_DELEGATESTES.get(mCurrentDelegate));

        mCurrentDelegate = tag;
    }
}
