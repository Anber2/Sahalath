package com.mawaqaa.sahalath.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.mawaqaa.applocationtrack.SettingsLocationTracker;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.volley.CommandFactory;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author josevieira
 */
public class LocationReceiver extends BroadcastReceiver {

    private static final int TWO_MINUTES = 1000 * 60 * 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        float speedOfDriver = 0;
        if (null != intent && intent.getAction().equals("my.action")) {
            Location locationData = (Location) intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
            Log.d("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());
            //send your call to api or do any things with the of location data
            JSONObject mJsonPostParam = new JSONObject();

            Toast.makeText(context, "Sahalat Driver get New Location with Long is : " + String.valueOf(locationData.getLongitude()) + " And With Lat is : " + String.valueOf(locationData.getLatitude()), Toast.LENGTH_LONG).show();
            speedOfDriver = locationData.getSpeed();
            if(AppConstants.LOCATION_TRACK_PREVIOUS == null){
                AppConstants.LOCATION_TRACK_PREVIOUS = locationData;
                try {
                    mJsonPostParam.put("id", AppConstants.SAHALAT_USER_ID_LOGIN);
                    mJsonPostParam.put("long", AppConstants.LOCATION_TRACK_PREVIOUS.getLongitude());
                    mJsonPostParam.put("lat", AppConstants.LOCATION_TRACK_PREVIOUS.getLatitude());
                } catch (JSONException e) {
                    e.printStackTrace();
                }catch (Exception ex){
                    String generalError = ex.getMessage();
                }

                CommandFactory mCommandFactory = new CommandFactory();
                mCommandFactory.sendPostCommand("", AppConstants.locationUrl(), mJsonPostParam, null, null);
            }else if(AppConstants.LOCATION_TRACK_NEW == null) {
                AppConstants.LOCATION_TRACK_NEW = locationData;

                boolean checkBestLocation = isBetterLocation(AppConstants.LOCATION_TRACK_NEW, AppConstants.LOCATION_TRACK_PREVIOUS);

                if (checkBestLocation == true) {

                    try {
                        mJsonPostParam.put("id", AppConstants.SAHALAT_USER_ID_LOGIN);
                        mJsonPostParam.put("long", AppConstants.LOCATION_TRACK_NEW.getLongitude());
                        mJsonPostParam.put("lat", AppConstants.LOCATION_TRACK_NEW.getLatitude());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception ex) {
                        String generalError = ex.getMessage();
                    }

                    CommandFactory mCommandFactory = new CommandFactory();
                    mCommandFactory.sendPostCommand("", AppConstants.locationUrl(), mJsonPostParam, null, null);

                }


                }else{

                    AppConstants.LOCATION_TRACK_PREVIOUS = AppConstants.LOCATION_TRACK_NEW;
                    AppConstants.LOCATION_TRACK_NEW = locationData;

                    boolean checkBestLocation = isBetterLocation(AppConstants.LOCATION_TRACK_NEW, AppConstants.LOCATION_TRACK_PREVIOUS);

                    if (checkBestLocation == true) {

                        try {
                            mJsonPostParam.put("id", AppConstants.SAHALAT_USER_ID_LOGIN);
                            mJsonPostParam.put("long", AppConstants.LOCATION_TRACK_NEW.getLongitude());
                            mJsonPostParam.put("lat", AppConstants.LOCATION_TRACK_NEW.getLatitude());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception ex) {
                            String generalError = ex.getMessage();
                        }

                        CommandFactory mCommandFactory = new CommandFactory();
                        mCommandFactory.sendPostCommand("", AppConstants.locationUrl(), mJsonPostParam, null, null);

                    }


            }
            }

        }



    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }


}