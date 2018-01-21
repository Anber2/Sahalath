package com.mawaqaa.sahalath;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SignInActivity;
import com.mawaqaa.sahalath.customviews.CircleProgressBar;

/**
 * Created by anson on 2/28/2017.
 */

public class SplashActivity extends SahalathBaseActivity {
    private static final String TAG = "SplashActivity";
    public SahalathBaseActivity Activity;
    CircleProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "Oncreate");
        super.onCreate(savedInstanceState);
        Activity = this.getSahalathBaseActivity();
        setContentView(R.layout.activity_splash);
        progressBar = (CircleProgressBar) findViewById(R.id.custom_progressBar);
        progressBar.setProgress(0);

//        startProgress();
        new ShowSpinnerAsyncTask().execute();
      /*  if (isNetworkAvailable()) {
        } else {
            Toast.makeText(Activity, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
            this.finish();
        }*/
    }


    private class ShowSpinnerAsyncTask extends
            AsyncTask<Void, Integer, Void> {
        float myProgress;

        @Override
        protected void onPreExecute() {
            myProgress = (float) 0.0;
            Log.e(TAG, "kjhfdvnlm");
        }

        @Override
        protected Void doInBackground(Void... params) {
            while (myProgress < 100) {
                myProgress++;
                SystemClock.sleep(5);
                publishProgress((int) myProgress);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


                progressBar.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            finish();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
        }
    }
}
