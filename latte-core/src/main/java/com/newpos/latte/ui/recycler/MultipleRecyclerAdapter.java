package com.newpos.latte.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.newpos.latte.R;
import com.newpos.latte.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener{


    private boolean mIsInitBanner = false;

    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data){
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter){
        return new MultipleRecyclerAdapter(converter.convert());
    }

    private void init(){
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.IMAGE_TEXT, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNERS, R.layout.item_multiple_banner);

        setSpanSizeLookup(this);
        openLoadAnimation();
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {

        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()){
            case ItemType.TEXT:
                text = item.getField(MultipleFileds.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
             imageUrl = item.getField(MultipleFileds.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .into((ImageView) holder.getView(R.id.image_single));
             break;
            case ItemType.IMAGE_TEXT:
                text = item.getField(MultipleFileds.TEXT);
                holder.setText(R.id.text_multiple, text);
                imageUrl = item.getField(MultipleFileds.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .into((ImageView) holder.getView(R.id.image_multiple));
                break;
            case ItemType.BANNERS:

                if(!mIsInitBanner){
                    bannerImages = item.getField(MultipleFileds.BANNERES);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;

                }
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFileds.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
