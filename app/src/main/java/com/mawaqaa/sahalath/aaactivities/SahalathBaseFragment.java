package com.mawaqaa.sahalath.aaactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.mawaqaa.sahalath.aacustomer.Data.SearchFoodTypeData;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by anson on 2/28/2017.
 */

public class SahalathBaseFragment extends Fragment {
    private static final String TAG = "SahalathBaseFragment";
    public SahalathBaseActivity Activity;

    ArrayList<SearchFoodTypeData.RestaurantPlaceData> placeDataList;
    ArrayList<SearchFoodTypeData> searchFoodTypeDatas = new ArrayList<SearchFoodTypeData>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "oncreate>>");
        Activity = (SahalathBaseActivity) this.getActivity();
    }

    public void onResume() {
        Log.d(TAG, "onResume" + this.getClass().getName());
        super.onResume();
        ((SahalathBaseActivity) getActivity()).BaseFragment = this;
    }

    public  void onHomePageDetailsSuccess(JSONObject jsonObject) {



    }

    public  void onHomePageDetailsFailed(JSONObject jsonObject) {
    }

}
