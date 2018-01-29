package com.newpos.latte.util;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class StringUtil {

    public static boolean isEmpty(String str){
        if(str == null || str.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isNotEmpty(String str){
       return !isEmpty(str);
    }
}
