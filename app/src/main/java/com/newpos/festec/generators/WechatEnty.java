package com.newpos.festec.generators;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

import com.newpos.latte.annotation.EntryGenerator;
import com.newpos.latte.wechat.template.WXEntryTemplate;

@EntryGenerator(
        packageName = "com.newpos.festec",
        entryTemplate = WXEntryTemplate.class
)
public interface WechatEnty {
}
