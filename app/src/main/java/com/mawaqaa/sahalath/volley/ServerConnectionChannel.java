package com.mawaqaa.sahalath.volley;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anson on 1/23/2017.
 */

public class ServerConnectionChannel {
    private static final String TAG = "ServerConnectionChannel";
    private int BABTAIN_BACKOFF_MULT = 2;
    private int BABTAIN_MAX_RETRIES = 2;
   static SahalathBaseActivity mActivty;
   static Activity mActivityOne;
   static Fragment mFragment;


    public ServerConnectionChannel() {
    }

    public void doSendJsonRequest(SahalathBaseActivity activity, Fragment fragment, SahalathRequest sahalathRequest) {

        RequestQueue queue = VolleyUtils.getRequestQueue();
        try {
            mActivty = activity;
            mFragment = fragment;
            JSONObject jsonObject = sahalathRequest.jsonObject;

            SahalathJsonRequest myReq = new SahalathJsonRequest(
                    sahalathRequest.method, sahalathRequest.mReqUrl,
                    jsonObject, createReqSuccessListener(mActivty, mFragment, sahalathRequest),
                    createReqErrorListener(sahalathRequest)) {
                protected Map<String, String> getParams()
                        throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "text/json");
                    return params;
                }
            };

            myReq.setRetryPolicy(new DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 20,
                    BABTAIN_MAX_RETRIES,
                    BABTAIN_BACKOFF_MULT));

            //myReq.setHeader("Cache-Control", "no-cache");
            //myReq.setHeader("Content-Type", "text/json");

            queue.add(myReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void doSendJsonRequest(Activity activity, Fragment fragment, SahalathRequest sahalathRequest) {

        RequestQueue queue = VolleyUtils.getRequestQueue();
        try {
             mActivityOne= activity;
            mFragment = fragment;
            JSONObject jsonObject = sahalathRequest.jsonObject;

            SahalathJsonRequest myReq = new SahalathJsonRequest(
                    sahalathRequest.method, sahalathRequest.mReqUrl,
                    jsonObject, createReqSuccessListener(mActivityOne, mFragment, sahalathRequest),
                    createReqErrorListener(sahalathRequest)) {
                protected Map<String, String> getParams()
                        throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "text/json");
                    return params;
                }
            };

            myReq.setRetryPolicy(new DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 20,
                    BABTAIN_MAX_RETRIES,
                    BABTAIN_BACKOFF_MULT));

            //myReq.setHeader("Cache-Control", "no-cache");
            //myReq.setHeader("Content-Type", "text/json");

            queue.add(myReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Response.ErrorListener createReqErrorListener(final SahalathRequest sahalathRequest) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            //    AppConstants.VIEW_CAT_LOADING.dismissAllowingStateLoss();
                Log.d(TAG, "ReqErrorListener" + error.getMessage());
                SahalathResponse batainResponse = new SahalathResponse();
                batainResponse.mReqUrl = sahalathRequest.mReqUrl;
                SahalathBaseActivity.getSahalathBaseActivity().serviceResponseError(batainResponse);
            }
        };
    }

    private Response.Listener<JSONObject> createReqSuccessListener(final SahalathBaseActivity activity, final Fragment fragment, final SahalathRequest sahalathRequest) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "ReqSuccessListener :" + sahalathRequest.mReqUrl);
                SahalathResponse batainResponse = new SahalathResponse();
                batainResponse.mReqUrl = sahalathRequest.mReqUrl;
                batainResponse.mFunction = sahalathRequest.mFunction;
                batainResponse.jsonObject = response;
                SahalathBaseActivity.getSahalathBaseActivity().serviceResponseSuccess(activity, fragment, batainResponse);
            }
        };
    }




    private Response.Listener<JSONObject> createReqSuccessListener(final Activity activity, final Fragment fragment, final SahalathRequest sahalathRequest) {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "ReqSuccessListener :" + sahalathRequest.mReqUrl);
                SahalathResponse batainResponse = new SahalathResponse();
                batainResponse.mReqUrl = sahalathRequest.mReqUrl;
                batainResponse.mFunction = sahalathRequest.mFunction;
                batainResponse.jsonObject = response;



                SahalathBaseActivity.getSahalathBaseActivity().serviceResponseSuccess(activity, fragment, batainResponse);
            }
        };
    }





}
