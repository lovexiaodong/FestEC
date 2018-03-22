package com.newpos.festec.generators;

import com.newpos.latte.annotation.PayEntyGenerator;
import com.newpos.latte.wechat.template.AppRegisterTemplate;

/**
 * Created by Administrator on 2018/3/23 0023.
 */
@PayEntyGenerator(
        packageName = "com.newpos.festec",
        payEntryTemplate = AppRegisterTemplate.class
)
public interface WechatPayEnty {
}
