package com.newpos.latte.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.newpos.latte.app.Latte;

/**
 * Created by Administrator on 2018/3/4 0004.
 */

public class LattePreference {


    private static final SharedPreferences PREFERENCES = PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());


    private static SharedPreferences getPreferences(){
        return PREFERENCES;
    }

    public static void clearAppPrefrences(){
        getPreferences().edit().clear().apply();
    }

    public static void setAppFlag(String key, boolean val){
        getPreferences()
                .edit()
                .putBoolean(key,val)
                .apply();
    }

    public static boolean getAppFlag(String key){
        return getPreferences()
                .getBoolean(key, false);
    }


    public static void addCustomAppProfile(String key, String val){
        getPreferences().edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key, String defaultValue){
        return getPreferences()
                .getString(key, defaultValue);
    }

}
