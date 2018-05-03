package com.newpos.latte.ui.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.newpos.latte.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> banner, ArrayList<String> data, OnItemClickListener clickListener){

        banner.setPages(new HolderCreator(), data)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setCanLoop(true);
    }
}
