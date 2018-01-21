package com.mawaqaa.sahalath.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 2/28/2017.
 */

public class PreferenceUtil {
    private final static String TAG = "PreferenceUtil";
    public static SharedPreferences sp;


    /*Language Preference Part*/
    public final static void setLanguage(Context context, String lang) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(AppConstants.SAHALATH_PREF_LANGUAGE, lang).apply();
    }

    public final static String getLanguage(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(AppConstants.SAHALATH_PREF_LANGUAGE, AppConstants.SAHALATH_ENGLISH);
    }

    /*Login Preference*/
    public final static void setIsLoggedIn(Context context, boolean isLoggedIn) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(AppConstants.SAHALATH_PREF_IS_LOGGED_IN, isLoggedIn).apply();
    }

    public final static boolean getIsLoggedIn(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(AppConstants.SAHALATH_PREF_IS_LOGGED_IN, false);
    }

    public final static void setUserId(Context context, String userId) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(AppConstants.SAHALATH_PREF_USER_ID, userId).apply();
    }

    public final static String getUserId(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(AppConstants.SAHALATH_PREF_USER_ID, null);
    }

    public final static void setUserType(Context context, int userType) {
        Log.e(TAG,"UserType Preference"+userType);
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(AppConstants.SAHALATH_PREF_USER_TYPE, userType).apply();
    }

    public final static int getUserType(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(AppConstants.SAHALATH_PREF_USER_TYPE, 0);
    }
}
