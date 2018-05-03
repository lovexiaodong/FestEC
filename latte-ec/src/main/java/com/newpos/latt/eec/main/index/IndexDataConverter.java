package com.newpos.latt.eec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.newpos.latte.ui.recycler.DataConverter;
import com.newpos.latte.ui.recycler.ItemType;
import com.newpos.latte.ui.recycler.MultipleFileds;
import com.newpos.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class IndexDataConverter extends DataConverter{


    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for(int i = 0; i < size; i++){
            final JSONObject data = dataArray.getJSONObject(i);
            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final String id = data.getString("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();

            int type = 0;
            if(imageUrl == null && text != null){
                type = ItemType.TEXT;
            }else if(imageUrl != null && text == null){
                type = ItemType.IMAGE;
            }else if(imageUrl != null){
                type = ItemType.IMAGE_TEXT;
            }else if(banners != null){
                type = ItemType.BANNERS;
                final int bannerSize = banners.size();
                for(int j = 0; j < bannerSize; j++){
                    bannerImages.add((String) banners.get(j));
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFileds.ITEM_TYPE, type)
                    .setField(MultipleFileds.ID, id)
                    .setField(MultipleFileds.SPAN_SIZE, spanSize)
                    .setField(MultipleFileds.TEXT, text)
                    .setField(MultipleFileds.IMAGE_URL, imageUrl)
                    .setField(MultipleFileds.BANNERES, bannerImages)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
