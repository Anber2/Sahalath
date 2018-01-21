package com.mawaqaa.sahalath;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.mawaqaa.sahalath.contants.AppConstants;


/**
 * Created by Ravi Tamada on 08/08/16.
 * www.androidhive.info
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        AppConstants.USER_DEVICE_ID = refreshedToken;
    //    AppConstants.USER_DEVICE_ID = refreshedToken;
        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
//        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
//        registrationComplete.putExtra("token", refreshedToken);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("regId", token);
//        editor.commit();
    }
}
