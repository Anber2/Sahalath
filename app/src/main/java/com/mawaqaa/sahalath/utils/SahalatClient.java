package com.mawaqaa.sahalath.utils;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by P_BMB on 22-Nov-17.
 */

public class SahalatClient {

    public static AsyncHttpClient GetClient(Context c)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(100000);

        return  client;
    }


}
