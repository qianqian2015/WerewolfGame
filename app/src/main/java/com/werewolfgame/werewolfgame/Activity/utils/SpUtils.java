package com.werewolfgame.werewolfgame.Activity.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/6/16.
 */
public class SpUtils {
    private static final String SP_NAME = "common";
    private static SpUtils mSpUtils;
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SpUtils(Context context) {
        this.context = context;
        sp = this.context.getSharedPreferences(SP_NAME, Context.MODE_APPEND);
        editor = sp.edit();
    }

    public static SpUtils getInstance(Context context) {

        if (mSpUtils == null) {
            synchronized (SpUtils.class) {
                if (mSpUtils == null) {
                    mSpUtils = new SpUtils(context);
                    return mSpUtils;
                }
            }
        }

        return mSpUtils;

    }

    public void putString(String key, String value) {
        if (key == null) {
            return;
        }
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }
    public void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, Boolean defValue) {
        return sp.getBoolean(key, defValue);
    }
}
