package com.werewolfgame.werewolfgame.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.werewolfgame.werewolfgame.Activity.utils.Utils;

/**
 * Created by Administrator on 2016/2/7.
 */
public class CommonTools {
    public static final String HUM_NUM = "hum_num";
    public static final String HAS_MODER = "has_moder";

    public static void setIntSp(Context context, String key, int value) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("werewolfgame",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setBooleanSp(Context context, String key, boolean value) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("werewolfgame",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static int getIntSp(Context context, String key) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("werewolfgame",
                Activity.MODE_PRIVATE);
        return mySharedPreferences.getInt(key, Utils.MIN_PLAYER_NUM);
    }

    public static boolean getBooleanSp(Context context, String key) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("werewolfgame",
                Activity.MODE_PRIVATE);
        return mySharedPreferences.getBoolean(key, false);
    }


}
