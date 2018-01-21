package com.mawaqaa.sahalath.volley;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;

import org.json.JSONObject;

/**
 * Created by anson on 1/23/2017.
 */

public class CommandFactory {


    public void sendGetCommand(Activity activity, Fragment fragment, String url) {
        ServerConnectionChannel serverConnectionChannel = VolleyUtils
                .getServerConnectionChannel();
        serverConnectionChannel.doSendJsonRequest(activity, fragment, createGetRequest(url));
    }

    public void sendGetCommand(SahalathBaseActivity activity, Fragment fragment, String url) {
        ServerConnectionChannel serverConnectionChannel = VolleyUtils
                .getServerConnectionChannel();
        serverConnectionChannel.doSendJsonRequest(activity, fragment, createGetRequest(url));
    }


    public void sendGetCommand(String url, String function, SahalathBaseActivity activity, Fragment fragment) {


        ServerConnectionChannel serverConnectionChannel = VolleyUtils
                .getServerConnectionChannel();
        serverConnectionChannel.doSendJsonRequest(activity, fragment, createGetRequest(url, function));
    }


    public void sendPostCommand(String url, JSONObject jsonObject, Activity activity, Fragment fragment) {
        ServerConnectionChannel serverConnectionChannel = VolleyUtils
                .getServerConnectionChannel();
        serverConnectionChannel.doSendJsonRequest(SahalathBaseActivity.getSahalathBaseActivity(), fragment, createPostRequest(url, jsonObject));
    }



    public void sendPostCommand(String function, String url, JSONObject jsonObject, SahalathBaseActivity activity, Fragment fragment) {
        ServerConnectionChannel serverConnectionChannel = VolleyUtils
                .getServerConnectionChannel();
        serverConnectionChannel.doSendJsonRequest(activity, fragment, createPostRequest(function, url, jsonObject));
    }


    /*method for get method*/
    private SahalathRequest createGetRequest(String url) {
        SahalathRequest babtainRequest = new SahalathRequest();
        babtainRequest.method = Request.Method.GET;
        babtainRequest.mReqUrl = url;
        return babtainRequest;
    }



    /*method for get method*/
    private SahalathRequest createGetRequest(String url, String functionDO) {
        SahalathRequest babtainRequest = new SahalathRequest();
        babtainRequest.method = Request.Method.GET;
        babtainRequest.mReqUrl = url;
        babtainRequest.mFunction = functionDO;
        return babtainRequest;
    }



    /*method for post method*/
    private SahalathRequest createPostRequest(String url, JSONObject jsonObject) {
        SahalathRequest babtainRequest = new SahalathRequest();
        babtainRequest.method = Request.Method.POST;
        babtainRequest.mReqUrl = url;
        babtainRequest.jsonObject = jsonObject;
        return babtainRequest;
    }




    /*method for post method*/
    private SahalathRequest createPostRequest(String function, String url, JSONObject jsonObject) {
        SahalathRequest babtainRequest = new SahalathRequest();
        babtainRequest.method = Request.Method.POST;
        babtainRequest.mReqUrl = url;
        babtainRequest.mFunction = function;
        babtainRequest.jsonObject = jsonObject;
        return babtainRequest;
    }


}
