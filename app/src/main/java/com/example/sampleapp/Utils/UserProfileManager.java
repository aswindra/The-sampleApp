package com.example.sampleapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserProfileManager {
    private static final String PREFERENCES_NAME = "The_Sample_App";
    private static final String USER = "user";
    private static final String IS_TECH = "is_tech";
    private static final String IS_NORMAL = "is_tech";
    private static final String IS_MANAGE = "is_tech";


    public static void saveUser(Context context, String arraySize) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER, arraySize).apply();
    }

    public static String getUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER, "0");
    }

}
