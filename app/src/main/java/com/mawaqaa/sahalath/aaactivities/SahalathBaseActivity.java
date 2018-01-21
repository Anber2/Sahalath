package com.mawaqaa.sahalath.aaactivities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aacustomer.fragments.HomeFragment;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.customviews.spinner.GeometricProgressView;
import com.mawaqaa.sahalath.interfaces.LoadOrders;
import com.mawaqaa.sahalath.volley.SahalathResponse;
import com.mawaqaa.sahalath.volley.VolleyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by anson on 2/28/2017.
 */

public class SahalathBaseActivity extends AppCompatActivity {
    public static final String M_EMAIL_LOGIN = AppConstants.EMAIL;
    public static final String M_PASSWORD_LOGIN = AppConstants.PASSWORD;
    public static final String M_LOGIN_URL = AppConstants.loginUrl(M_EMAIL_LOGIN, M_PASSWORD_LOGIN, AppConstants.USER_DEVICE_ID);
    // can dismiss spinweheel afrer a
    // specific time
    public static final int SPINWHEEL_LIFE_TIME = 700;
    private static final String TAG = "SahalathBaseActivity";
    public static boolean isLoggedIn = false;
    protected static SahalathBaseActivity BaseActivity;
    static int USER_TYPE;
    public SignInActivity signInActivity;
    public SahalathBaseFragment BaseFragment;
    Handler spinWheelTimer = new Handler(); // Handler to post a runnable that
    private Dialog spinWheelDialog;
    /**
     * Dismiss the spinwheel
     **/
    Runnable dismissSpinner = new Runnable() {

        @Override
        public void run() {
            stopThemeSpinWheel();
        }

    };

    public static SahalathBaseActivity getSahalathBaseActivity() {
        return BaseActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "On create");
        VolleyUtils.init(this);
        BaseActivity = this;
    }

    public void serviceResponseSuccess(Activity activity, Fragment fragment, SahalathResponse response) {
        Log.d(TAG, "serviceResponseSuccess");
       // AppConstants.VIEW_CAT_LOADING.dismissAllowingStateLoss();
        if (response != null) {
            String reqUrl = response.mReqUrl;
            Log.d(TAG, "serviceResponseSuccess" + reqUrl);


//            switch (reqUrl) {
//                case M_LOGIN_URL:
//                    onLoginCompletedSuccessfully(response.jsonObject);
//                    break;
//                case AppConstants.SAHALATH_URL_REGISTRATION_EN:
//                    onRegistrationSpinnersSuccess(response.jsonObject);
//                    break;
//                case AppConstants.SAHALATH_URL_REGISTRATION_AR:
//                    onRegistrationSpinnersSuccess(response.jsonObject);
//                    break;
//                case AppConstants.SAHALATH_URL_ADD_USER:
//                    onAddUserSuccess(response.jsonObject);
//                    break;
//                case AppConstants.SAHALATH_URL_HOMEPAGE_EN:
//                    BaseFragment.onHomePageDetailsSuccess(response.jsonObject);
//                    break;
//
//                default:
//                    break;
//            }
//        }


            if (response.mFunction.equals("LOGIN")) {
                onLoginCompletedSuccessfully(response.jsonObject);
            } else if (response.mFunction.equals("ORDER_LOAD") && fragment != null) {
                JSONObject mObjectJson = response.jsonObject;
                try {
                    JSONArray mJsonArray = mObjectJson.getJSONArray("rows");
                    LoadOrders itemInterfaceLoadOrder = (LoadOrders) fragment;
                    itemInterfaceLoadOrder.loadOrders(mJsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (reqUrl.equals(AppConstants.SAHALATH_URL_REGISTRATION_EN)) {
                onRegistrationSpinnersSuccess(response.jsonObject);
            } else if (reqUrl.equals(AppConstants.SAHALATH_URL_REGISTRATION_AR)) {
                onRegistrationSpinnersSuccess(response.jsonObject);
            } else {

            }


        }

    }

    public void serviceResponseError(SahalathResponse response) {
        Log.d(TAG, "serviceResponseError!!!");

        if (response != null) {
            String reqUrl = response.mReqUrl;
            Log.d(TAG, "serviceResponseError!!!" + reqUrl);
//            switch (reqUrl) {
//                case M_LOGIN_URL:
//                    onLoginFailed(response.jsonObject);
//                    break;
//                case AppConstants.SAHALATH_URL_REGISTRATION_EN:
//                    onRegistrationSpinnersFailed(response.jsonObject);
//                    break;
//                case AppConstants.SAHALATH_URL_REGISTRATION_AR:
//                    onRegistrationSpinnersFailed(response.jsonObject);
//                    break;
//                case AppConstants.SAHALATH_URL_ADD_USER:
//                    onAddUserFailure(response.jsonObject);
//                    break;
//                case AppConstants.SAHALATH_URL_HOMEPAGE_EN:
//                    BaseFragment.onHomePageDetailsFailed(response.jsonObject);
//                    break;
//                default:
//                    break;
//            }

            if (reqUrl.equals(M_LOGIN_URL)) {
                onLoginFailed(response.jsonObject);
            }

        }
    }

    public void onLoginCompletedSuccessfully(JSONObject jsonObject) {
        try {
            JSONObject mJsonLoginResponse = jsonObject.getJSONObject("role");
            //String userRole = mJsonLoginResponse.getString("9");
            Iterator<String> keys = mJsonLoginResponse.keys();
            // get some_name_i_wont_know in str_Name
            String str_Name=keys.next();
            // get the value i care about
            String userRole = mJsonLoginResponse.optString(str_Name);

            Integer userID = jsonObject.getInt("user_id");
            AppConstants.SAHALAT_USER_TOKEN = jsonObject.getString("token");
            if (userID != null) {
                AppConstants.SAHALAT_USER_ID_LOGIN = userID;
            }
            if (userRole.equals("driver")) {
                USER_TYPE = AppConstants.USER_DRIVER;
                Intent intent = new Intent();
                intent.setClass(getSahalathBaseActivity(), SahalathMainActivity.class);
                startActivity(intent);
                finish();
            }else if(userRole.equals("service-boy")){
                USER_TYPE = AppConstants.USER_SERVICE_BOY;
                Intent intent = new Intent();
                intent.setClass(getSahalathBaseActivity(), SahalathMainActivity.class);
                startActivity(intent);
                finish();
            }

        } catch (JSONException e) {
            String jsonError = e.getMessage();
        } catch (Exception ex) {
            String generalError = ex.getMessage();
        }
    }

    public void onLoginFailed(JSONObject jsonObject) {
    }

    public void onRegistrationSpinnersSuccess(JSONObject jsonObject) {
    }

    public void onRegistrationSpinnersFailed(JSONObject jsonObject) {
    }

    public void onAddUserSuccess(JSONObject jsonObject) {
    }

    public void onAddUserFailure(JSONObject jsonObject) {
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

    public String getCurrentFragment() {
        String fragmentName;
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.e(TAG, ">>>>" + fragmentManager.getBackStackEntryCount());
        if (fragmentManager.getBackStackEntryCount() == 0) {
            fragmentName = HomeFragment.class.getName();
            Log.e(TAG, "stack count zero" + fragmentName);
        } else {
            String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
            Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
            fragmentName = currentFragment.getClass().getName();
        }
        return fragmentName;
    }

    public void pushFragments(Fragment fragment, boolean shouldAnimate,
                              boolean shouldAdd) {
        FragmentManager manager = getSupportFragmentManager();
        String backStateName = fragment.getClass().getName();

        if (isNeedTransaction(backStateName)) {
            boolean fragmentPopped = manager.popBackStackImmediate(
                    backStateName, 0);

            if (!fragmentPopped) { // fragment not in back stack, create it.
                FragmentTransaction ft = manager.beginTransaction();

                if (shouldAnimate)
                    ft.setCustomAnimations(R.anim.slide_in_right,
                            R.anim.slide_out_left);
                ft.replace(R.id.fragment_container, fragment, backStateName);
                if (shouldAdd)
                    ft.addToBackStack(backStateName);
                ft.commit();
                manager.executePendingTransactions();
            } else {

                FragmentTransaction ft = manager.beginTransaction();
                // ft.remove(fragment);
                if (shouldAnimate)
                    ft.setCustomAnimations(R.anim.slide_in_right,
                            R.anim.slide_out_left);
                ft.replace(R.id.fragment_container, fragment, backStateName);

                ft.commit();
                manager.executePendingTransactions();

            }
        } else {


            boolean fragmentPopped = manager.popBackStackImmediate(
                    backStateName, 0);

            if (!fragmentPopped) { // fragment not in back stack, create it.
                FragmentTransaction ft = manager.beginTransaction();

                if (shouldAnimate)
                    ft.setCustomAnimations(R.anim.slide_in_right,
                            R.anim.slide_out_left);
                ft.replace(R.id.fragment_container, fragment, backStateName);
                if (shouldAdd)
                    ft.addToBackStack(backStateName);
                ft.commit();
                manager.executePendingTransactions();
            } else {

                FragmentTransaction ft = manager.beginTransaction();
                ft.remove(fragment);
                if (shouldAnimate)
                    ft.setCustomAnimations(R.anim.slide_in_right,
                            R.anim.slide_out_left);
                ft.replace(R.id.fragment_container, fragment, backStateName);
                if (shouldAdd)
                    ft.addToBackStack(backStateName);
                ft.commit();
                manager.executePendingTransactions();

            }


        }
    }

    public void clearAllBackStackEntries() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);

    }

    public void popFragments(Fragment frag) {
        Log.e(TAG, "Inside pop fragment");
        try {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            String fragName = frag.getClass().getName();
            manager.popBackStack(fragName,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.remove(frag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean isNeedTransaction(String backStateName) {
        boolean needTransaction = true;
        if (BaseFragment != null) {
            String baseFrag = BaseFragment.getClass().getName();
            if (baseFrag.equals(backStateName)) {
                needTransaction = false;
            } else
                needTransaction = true;
        }
        return needTransaction;
    }

    public void startThemeSpinningWheel() {
        if (spinWheelDialog != null && spinWheelDialog.isShowing()) {
            return;
        }
        spinWheelDialog = new Dialog(this, R.style.wait_spinner_style);
        GeometricProgressView progressView = new GeometricProgressView(this);
        android.app.ActionBar.LayoutParams layoutParams = new android.app.ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
        spinWheelDialog.addContentView(progressView, layoutParams);
        spinWheelDialog.setCancelable(false);
        spinWheelDialog.show();
    }

    /**
     * Closes the spin wheel dialog*
     */

    public void stopThemeSpinWheel() {
        // Log.d(TAG, "stopThemeSpinWheel"+getCurrentActivity().getClass());
        if (spinWheelDialog != null)
            try {
                spinWheelDialog.dismiss();
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Parent is died while tryingto dismiss spin wheel dialog ");
                e.printStackTrace();
            }
        spinWheelDialog = null;
    }

    // Callback for spin wheel dismissal
    protected void onSpinWheelDismissed() {
        Log.d(TAG, "Spin wheel disconnected");
    }


}
