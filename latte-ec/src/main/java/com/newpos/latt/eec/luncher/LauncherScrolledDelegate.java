package com.newpos.latt.eec.luncher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.newpos.latt.eec.R;
import com.newpos.latte.delegates.LatterDelegate;
import com.newpos.latte.ui.launcher.LauncherCBViewHolderCreator;
import com.newpos.latte.ui.launcher.LauncherTag;
import com.newpos.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/5 0005.
 */

public class LauncherScrolledDelegate extends LatterDelegate implements OnItemClickListener{
    private ConvenientBanner<Integer> mConvenientBananer;

    private static ArrayList<Integer> list = new ArrayList<>();

    private void initBanner(){
        list.add(R.mipmap.launcher_01);
        list.add(R.mipmap.launcher_02);
        list.add(R.mipmap.launcher_03);
        list.add(R.mipmap.launcher_04);
        list.add(R.mipmap.launcher_05);
        mConvenientBananer.setPages(new LauncherCBViewHolderCreator(), list)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true);
    }

    @Override
    public Object setLayout() {
        mConvenientBananer = new ConvenientBanner<>(_mActivity);
        return mConvenientBananer;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {

        if(position == list.size() -1){
            LattePreference.setAppFlag(LauncherTag.FIRST_LAUNCHER_TAG.name(), true);
        }
    }
}
