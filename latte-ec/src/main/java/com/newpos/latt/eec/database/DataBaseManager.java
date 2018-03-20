package com.newpos.latt.eec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class DataBaseManager {

    private DaoSession mDaoSesssion = null;

    private UserProfileDao mDao = null;

    private DataBaseManager(){}


    private static final class Holder{
        private static final DataBaseManager MANAGER = new DataBaseManager();
    }

    public static DataBaseManager getInstance(){
        return Holder.MANAGER;
    }

    public DataBaseManager init(Context context){
        initDao(context);
        return this;
    }

    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "user_profile.db");
        final Database database = helper.getWritableDb();
        mDaoSesssion = new DaoMaster(database).newSession();
        mDao = mDaoSesssion.getUserProfileDao();
    }

    public UserProfileDao getDao(){
        return mDao;
    }
}
