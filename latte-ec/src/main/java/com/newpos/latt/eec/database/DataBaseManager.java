package com.newpos.latt.eec.database;

import android.content.Context;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class DataBaseManager {

    private DaoSession mDaoSesssion = null;

    private UserProfileDao mDao = null;

    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "user_profile.db");
    }
}
