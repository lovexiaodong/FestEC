package com.newpos.latte.ui.recycler;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class MultiplelItemEntityBuilder {
    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultiplelItemEntityBuilder(){
        FIELDS.clear();
    }

    public final MultiplelItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFileds.ITEM_TYPE, itemType);
        return this;
    }
    public final MultiplelItemEntityBuilder setField(Object key, Object value){
        FIELDS.put(key, value);
        return this;
    }

    public final MultiplelItemEntityBuilder setFields(LinkedHashMap<?,?> map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
        return new MultipleItemEntity(FIELDS);
    }
}
