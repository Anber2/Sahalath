package com.mawaqaa.sahalath.aaactivities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.utils.LocalHelper;
import com.mawaqaa.sahalath.utils.PreferenceUtil;
import com.mawaqaa.sahalath.volley.CommandFactory;

import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by anson on 2/28/2017.
 */

public class SignInActivity extends SahalathBaseActivity implements View.OnClickListener {
    private static final String TAG = "SignInActivity";
    public SahalathBaseActivity Activity;
    Button btnLogin, btnRegister;
    TextView textViewSkipp, textViewForgotPassword;
    EditText editTextUserName, editTextPassword;
    ImageButton toolbarAccount, drawerToggleButton, toolBarLanguage, toolbarUpButton;
    String userName = "", isLoginSuccess = "";
    ProgressDialog mProgressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = getSahalathBaseActivity();

        mProgressDialog = new ProgressDialog(SignInActivity.this, R.style.wait_spinner_style);
       mProgressDialog.setMessage(getResources().getString(R.string.loading));
       mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        checkForLogin();



    }

    private void checkForLogin() {
        if (PreferenceUtil.getIsLoggedIn(Activity)) {
            Log.e(TAG, "USerIs Logged In>>>" + PreferenceUtil.getUserType(Activity));
            loginUserType(PreferenceUtil.getUserType(Activity));
        } else {
            Log.e(TAG, "User is not Logged In");
            setContentView(R.layout.activity_login);
            initView();
        }
    }


    private void initView() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        textViewForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        textViewSkipp = (TextView) findViewById(R.id.tv_skipp_login);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        editTextUserName = (EditText) findViewById(R.id.et_username);

        drawerToggleButton = (ImageButton) findViewById(R.id.img_btn_appbar_toggle_drawer);
        toolbarUpButton = (ImageButton) findViewById(R.id.img_btn_appbar_up);
        toolbarAccount = (ImageButton) findViewById(R.id.img_btn_appbar_account);
        toolBarLanguage = (ImageButton) findViewById(R.id.img_btn_appbar_lang);

        drawerToggleButton.setVisibility(View.GONE);
        toolbarAccount.setVisibility(View.GONE);
        toolbarUpButton.setVisibility(View.GONE);

        toolBarLanguage.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        textViewSkipp.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_appbar_lang:
                Log.e(TAG, "Language Switch");

                String storedLanguage = LocalHelper.getLanguage(SignInActivity.this);
                if(storedLanguage == null || storedLanguage.equals("")){
                    String currentLanguage = Locale.getDefault().getLanguage();
                    LocalHelper.setLocale(SignInActivity.this, currentLanguage);
                }else if(storedLanguage.equals("en")){
                    LocalHelper.setLocale(SignInActivity.this, "ar");
                }else if(storedLanguage.equals("ar")) {
                    LocalHelper.setLocale(SignInActivity.this, "en");
                }else{

                }
                break;
            case R.id.btn_login:

                startLogin();
                break;
            case R.id.btn_register:
                registerAccount();
                break;
            case R.id.tv_skipp_login:
                skippLogin();
                break;
            default:
                break;
        }
    }





    private void startLogin() {
        try {
            if (Activity.isNetworkAvailable()) {
                if (nullCheck()) {
                    if (editTextUserName.getText().toString().matches(AppConstants.EMAIL_PATTERN)) {
                        if (editTextPassword.getText().toString().length() >= 1) {
                            createJsonData();
                        } else {
                            Toast.makeText(Activity, getString(R.string.alert_invalid_password), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(Activity, getResources().getString(R.string.alert_invalid_email), Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                Toast.makeText(Activity, getString(R.string.alert_no_internet), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createJsonData() {



//        if (editTextUserName.getText().toString().equals("customer@sahalat.com")) {
//
//            Activity.USER_TYPE = AppConstants.USER_CUSTOMER;
//            startSahalathMainActivity();
//
//
//        } else if (editTextUserName.getText().toString().equals("driver@sahalat.com")) {
//            Activity.USER_TYPE = AppConstants.USER_DRIVER;
//            startSahalathMainActivity();
//
//        } else if (editTextUserName.getText().toString().equals("serviceboy@sahalat.com")) {
//            Activity.USER_TYPE = AppConstants.USER_SERVICE_BOY;
//            startSahalathMainActivity();
//
//        }


      /*  try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_LOGIN_MAIL, editTextUserName.getText().toString());
            jsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_LOGIN_PASSWORD, editTextPassword.getText().toString());
            Log.e(TAG, "Login Request" + jsonObject);
            startThemeSpinningWheel();
            if (VolleyUtils.volleyEnabled) {
                CommandFactory commandFactory = new CommandFactory();
                commandFactory.sendPostCommand(AppConstants.SAHALATH_URL_LOGIN, jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        //AppConstants.VIEW_CAT_LOADING.show(getSupportFragmentManager(), "");
        mProgressDialog.show();
        JSONObject mJsonObject = new JSONObject();
        CommandFactory mCommandFactory = new CommandFactory();
        mCommandFactory.sendPostCommand("LOGIN", AppConstants.loginUrl(editTextUserName.getText().toString(), editTextPassword.getText().toString(), AppConstants.USER_DEVICE_ID), mJsonObject, SignInActivity.this, null);

    }




    @Override
    public void onLoginCompletedSuccessfully(JSONObject jsonObject) {
        super.onLoginCompletedSuccessfully(jsonObject);
        Log.e(TAG, "Login Response success>>" + jsonObject);
        mProgressDialog.dismiss();
        parseJson(jsonObject);
    }

    private void parseJson(JSONObject jsonObject) {
        try {
            if (jsonObject != null) {
                isLoginSuccess = jsonObject.getString(AppConstants.SAHALATH_JSON_TAG_SUCCESS);
                Log.e(TAG, "LoginSuccess >>" + isLoginSuccess);

                if (isLoginSuccess.equals(AppConstants.SAHALATH_JSON_TAG_SUCCESS)) {
                    String userId = jsonObject.getString(AppConstants.SAHALATH_JSON_TAG_USER_ID);
                    Log.e(TAG, "userId >>>>" + userId + "<<<<<<<Usertype>>>" + Integer.parseInt(jsonObject.getString(AppConstants.SAHALATH_JSON_TAG_USER_TYPE)));
                    PreferenceUtil.setIsLoggedIn(Activity, true);
                    PreferenceUtil.setUserId(Activity, userId);
                    PreferenceUtil.setUserType(Activity, Integer.parseInt(jsonObject.getString(AppConstants.SAHALATH_JSON_TAG_USER_TYPE)));
                    stopThemeSpinWheel();
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_success), Toast.LENGTH_LONG).show();

                    loginUserType(Integer.parseInt(jsonObject.getString(AppConstants.SAHALATH_JSON_TAG_USER_TYPE)));
                } else {
                    stopThemeSpinWheel();
                    Toast.makeText(this, getResources().getString(R.string.login_failed), Toast.LENGTH_LONG).show();
                }
            } else {
                stopThemeSpinWheel();
                Toast.makeText(this, getResources().getString(R.string.server_error_connection), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoginFailed(JSONObject jsonObject) {
        super.onLoginFailed(jsonObject);
        Log.e(TAG, "Login response failed>>>" + jsonObject);
        stopThemeSpinWheel();
        Toast.makeText(this, getResources().getString(R.string.server_error_connection), Toast.LENGTH_LONG).show();
    }

    public void registerAccount() {
        Intent registerIntent = new Intent();
        registerIntent.setClass(Activity, RegisterActivity.class);
        startActivity(registerIntent);
        finish();
    }

    public void onForgotPassword() {

    }

    public void startSahalathMainActivity() {
        Intent intent = new Intent();
        intent.setClass(Activity, SahalathMainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean nullCheck() {
        if (editTextPassword.getText().toString().length() <= 0 ||
                editTextUserName.getText().toString().length() <= 0) {
            Toast.makeText(Activity, getString(R.string.alert_no_empty_field), Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseActivity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void loginUserType(int userType) {
        switch (userType) {
            case AppConstants.USER_CUSTOMER:
                Activity.USER_TYPE = AppConstants.USER_CUSTOMER;
                startSahalathMainActivity();
                break;

            case AppConstants.USER_DRIVER:
                Activity.USER_TYPE = AppConstants.USER_DRIVER;
                startSahalathMainActivity();
                break;

            case AppConstants.USER_SERVICE_BOY:
                Activity.USER_TYPE = AppConstants.USER_SERVICE_BOY;
                startSahalathMainActivity();
                break;

            default:
                break;
        }
    }

    public void skippLogin() {
        if(editTextUserName.getText().toString().equalsIgnoreCase("driver")){
            Activity.USER_TYPE = AppConstants.USER_DRIVER;
        }else if(editTextUserName.getText().toString().equalsIgnoreCase("boy")){
            Activity.USER_TYPE = AppConstants.USER_SERVICE_BOY;
        }else {
            Activity.USER_TYPE = AppConstants.USER_CUSTOMER;
        }
        startSahalathMainActivity();
    }

}
