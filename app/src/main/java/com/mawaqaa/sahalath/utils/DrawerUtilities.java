package com.mawaqaa.sahalath.utils;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;

import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 3/9/2017.
 */

public class DrawerUtilities {
    protected final static String TAG = "DrawerUtils";

    public static final void closeDrawerVeiw(Context context,
                                             DrawerLayout mDrawerLayout) {
        try {
            if (PreferenceUtil.getLanguage(context).equals(
                    AppConstants.SAHALATH_ENGLISH)) {
                mDrawerLayout.closeDrawer(Gravity.START);
            } else {
                mDrawerLayout.closeDrawer(Gravity.START);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in closeDrawer Method");
            e.printStackTrace();
        }
    }
}
