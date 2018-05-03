package com.newpos.latt.eec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public enum  ECIcons implements Icon {
    icon_weichat('\ue693'),
    icon_scan('\ue610'),
    icon_ali_pay('\ue502');

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
