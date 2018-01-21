package com.mawaqaa.sahalath.aacustomer.Data;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by anson on 3/20/2017.
 */

public class SearchFoodTypeData {
    public String restaurantTypeName, imageUrl, typeId;
    public ArrayList<RestaurantPlaceData> placeData;

    public SearchFoodTypeData(String restaurantTypeName, String imageUrl, String typeId, ArrayList<RestaurantPlaceData> placeData) {
        Log.e("SearchFoodTypeData", restaurantTypeName + ">>>" + imageUrl + ">>>>" + typeId);
        this.imageUrl = imageUrl;
        this.restaurantTypeName = restaurantTypeName;
        this.typeId = typeId;
        this.placeData = placeData;
    }

    public static class RestaurantPlaceData {
        public String placeName, placeId;

        public RestaurantPlaceData(String placeName, String placeId) {
            Log.e("RestaurantPc", placeId + ">>>" + placeName);
            this.placeName = placeName;
            this.placeId = placeId;
        }
    }
}
