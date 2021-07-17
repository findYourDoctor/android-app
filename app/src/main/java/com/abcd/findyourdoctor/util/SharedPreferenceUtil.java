package com.abcd.findyourdoctor.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public final class SharedPreferenceUtil {

    public static void setPreferences(Context context, String key, String value) {
        getEditor(context).putString(key, value).apply();
    }

    public static void setPreferences(Context context, String key, int value) {
        getEditor(context).putInt(key, value).apply();
    }

    public static void setPreferences(Context context, String key, Boolean value) {
        getEditor(context).putBoolean(key, value).apply();
    }

//    public static void setLongPreferences(Context context, String key, long value) {
//        getEditor(context).putLong(key, value).apply();
//    }

    public static void setFloatPreferences(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).apply();
    }

    public static void setObject(Context context, String key, Object object) {

        Gson gson = new Gson();
        String json = gson.toJson(object);
        getEditor(context).putString(key, json).apply();
    }

    public static String getPreferences(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static int getPreferences(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

//    public static Long getLongPreferences(Context context, String key, long defValue) {
//        return getPreferences(context).getLong(key, defValue);
//    }

    public static float getFloatPreferences(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public static Boolean getPreferences(Context context, String key, boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public static String getObject(Context context, String key) {

        return getPreferences(context).getString(key, null);
    }

    public static boolean isContains(Context context, String key) {
        return getPreferences(context).contains(key);
    }

    public static void remove(Context context, String key) {
        getEditor(context).remove(key).commit();
    }

    public static void clearAll(Context context) {
        getEditor(context).clear().apply();
    }

    private static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
