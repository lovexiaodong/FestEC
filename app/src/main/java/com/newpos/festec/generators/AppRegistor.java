package com.newpos.festec.generators;

import com.newpos.latte.annotation.AppRegisterGenerator;
import com.newpos.latte.wechat.template.AppRegisterTemplate;

/**
 * Created by Administrator on 2018/3/23 0023.
 */
@AppRegisterGenerator(
        packageName = "com.newpos.festec",
        registerTemplate = AppRegisterTemplate.class

)
public interface AppRegistor {
}
