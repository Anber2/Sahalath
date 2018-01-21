package com.mawaqaa.applocationtrack;

import android.location.Location;

/**
 * @author netodevel
 */
public interface CurrentLocationListener {

    /**
     * get current location
     */
    void onCurrentLocation(Location location);

    /**
     * Permission deined
     */
    void onPermissionDiened();

}
