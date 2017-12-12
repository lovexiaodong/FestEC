package com.newpos.latt.eec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public enum  ECIcons implements Icon {
    ICON_WEIXIN('\ue693'),
    ICON_SAOMA('\ue610'),
    ICON_ZHIFUBAO('\ue502');

    private char character;

    ECIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace("_", "-");
    }

    @Override
    public char character() {
        return character;
    }
}
