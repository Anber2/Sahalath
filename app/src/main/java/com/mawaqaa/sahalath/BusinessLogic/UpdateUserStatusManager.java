package com.mawaqaa.sahalath.BusinessLogic;

import android.app.Activity;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.utils.SahalatClient;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by P_BMB on 22-Nov-17.
 */

public class UpdateUserStatusManager {

    Activity contextActivity;


    public UpdateUserStatusManager(Activity activity, android.support.v4.app.Fragment fragment){
        if(activity == null){
            contextActivity = fragment.getActivity();
        }else{
            contextActivity = activity;
        }
    }



    public void updateUserStatus(String userId, String status){


      SahalatClient.GetClient(contextActivity).get(contextActivity, AppConstants.changeUserStatus(userId, status), new JsonHttpResponseHandler(){
          @Override
          public void onStart() {
              super.onStart();
          }

          @Override
          public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONArray response) {
              super.onSuccess(statusCode, headers, response);
              JSONArray JsnArray = response;
          }

          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
              super.onSuccess(statusCode, headers, response);
              JSONObject Jsn = response;
          }

          @Override
          public void onSuccess(int statusCode, Header[] headers, String responseString) {
              super.onSuccess(statusCode, headers, responseString);
              String seccucessString = responseString;
          }

          @Override
          public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
              super.onFailure(statusCode, headers, responseString, throwable);
              String error = responseString;
          }

          @Override
          public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
              super.onFailure(statusCode, headers, throwable, errorResponse);
              JSONArray mArray = errorResponse;
          }

          @Override
          public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
              super.onFailure(statusCode, headers, throwable, errorResponse);
              JSONObject errorResponseDebug = errorResponse;

          }
      });




    }



}
