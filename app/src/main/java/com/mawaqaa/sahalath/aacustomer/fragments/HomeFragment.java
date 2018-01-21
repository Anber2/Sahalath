package com.mawaqaa.sahalath.aacustomer.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.SearchFoodTypeData;
import com.mawaqaa.sahalath.aacustomer.Data.SearchFoodTypeData.RestaurantPlaceData;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.customviews.SearchCircleLayout;
import com.mawaqaa.sahalath.customviews.SearchCircleLayoutOuter;
import com.mawaqaa.sahalath.customviews.TextViewWithId;
import com.mawaqaa.sahalath.customviews.TitiledImageView;
import com.mawaqaa.sahalath.volley.CommandFactory;
import com.mawaqaa.sahalath.volley.VolleyUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by anson on 3/1/2017.
 */

public class HomeFragment extends SahalathBaseFragment implements View.OnClickListener,
        SearchCircleLayout.OnItemSelectedListener, SearchCircleLayoutOuter.OnOuterItemSelectedListener {
    public static final String TAG = "HomeFragment";
    public SahalathBaseActivity Activity;
    protected SearchCircleLayout circleLayout;
    protected SearchCircleLayoutOuter circleLayoutOuter;
    public static String selectedInnerId, selectedOuterId;

    private static final int ITEM_COUNT = 20;
    ImageButton btnSearch;
    ArrayList<SearchFoodTypeData> searchFoodTypeDataList;
    ArrayList<RestaurantPlaceData> placeDataList;
    ArrayList<SearchFoodTypeData> searchFoodTypeDatas = new ArrayList<SearchFoodTypeData>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.hideUpButton();
        SahalathMainActivity.hideBottomBar();
    //    Activity.startThemeSpinningWheel();
        loadHomePageDetails();
    }


    @Override
    public void onResume() {
        super.onResume();
        SahalathMainActivity.hideUpButton();
        SahalathMainActivity.hideBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_home, container, false);

        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        btnSearch = (ImageButton) v.findViewById(R.id.btn_home_search);
        circleLayout = (SearchCircleLayout) v.findViewById(R.id.circle_layout);
        circleLayoutOuter = (SearchCircleLayoutOuter) v.findViewById(R.id.circle_layout_outer);

        circleLayout.setOnItemSelectedListener(this);

        circleLayoutOuter.setOnOuterItemSelectedListener(this);
        circleLayoutOuter.setVisibility(View.GONE);
        btnSearch.setOnClickListener(this);
    }

    private void loadHomePageDetails() {
        if (Activity.isNetworkAvailable()) {
            if (VolleyUtils.volleyEnabled) {
                CommandFactory commandFactory = new CommandFactory();
                commandFactory.sendGetCommand(null, HomeFragment.this, AppConstants.SAHALATH_URL_HOMEPAGE_EN);
            }
        } else {
            Toast.makeText(Activity, getString(R.string.alert_no_internet), Toast.LENGTH_LONG).show();
        }
    }


    private void addItemsToSearch() {

        for (int i = 0; i < searchFoodTypeDatas.size(); i++) {
            TitiledImageView imageView = new TitiledImageView(getContext(), null);
            Log.e(TAG, "Image url" + searchFoodTypeDatas.get(i).imageUrl);
            Picasso.with(getContext()).load(searchFoodTypeDatas.get(i).imageUrl).resize(100, 100).into(imageView);
            imageView.setText(searchFoodTypeDatas.get(i).restaurantTypeName);
            Log.e("addsearch" + ">>>>", searchFoodTypeDatas.get(i).imageUrl);
            imageView.setContentDescription(searchFoodTypeDatas.get(i).typeId);
            imageView.setCustomFont("fonts/RobotoCondensed-Italic.ttf");
            imageView.setArrayLocation(i);
            imageView.setPadding(0, 0, 0, 16);
            circleLayout.addView(imageView, -1);
            Activity.stopThemeSpinWheel();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home_search:
                Fragment restaurantListPageFragment = new RestSearchListPageFragment();
                Activity.pushFragments(restaurantListPageFragment, true, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onHomePageDetailsSuccess(JSONObject jsonObject) {

        Log.e(TAG, "-----------onHomePageDetailsSuccess");
        super.onHomePageDetailsSuccess(jsonObject);

        if (jsonObject != null) {
            try {
                Log.e(TAG, "+++++++++++onHomePageDetailsSuccess");

                JSONArray cuisineArray = jsonObject.getJSONArray(AppConstants.SAHALATH_JSON_TAG_CUISINE_LIST);

                Log.e(TAG, "Cuisine Array" + cuisineArray);
                searchFoodTypeDatas = new ArrayList<>();
                for (int i = 0; i < cuisineArray.length(); i++) {
                    Log.e(TAG, "cuisineArray.length() === " + cuisineArray.length());

                    JSONObject cuisineObject = cuisineArray.getJSONObject(i);
                    Log.e(TAG, "CuisineObject>>>" + cuisineObject);
                    JSONArray cuisineAreaJsonArray = cuisineObject.getJSONArray(AppConstants.SAHALATH_JSON_TAG_CUISINE_AREAS);
                    placeDataList = new ArrayList<>();
                    for (int j = 0; j < cuisineAreaJsonArray.length(); j++) {
                        JSONObject placeJsonObject = cuisineAreaJsonArray.getJSONObject(j);
                        Log.e(TAG, "CuisineAreaObject>>>" + placeJsonObject);
                        SearchFoodTypeData.RestaurantPlaceData restaurantPlaceData = new SearchFoodTypeData.RestaurantPlaceData(
                                placeJsonObject.getString(AppConstants.SAHALATH_JSON_TAG_CUISINE_AREA_NAME),
                                placeJsonObject.getString(AppConstants.SAHALATH_JSON_TAG_CUISINE_AREA_ID));
                        placeDataList.add(restaurantPlaceData);
                    }
                    SearchFoodTypeData searchFoodTypeData = new SearchFoodTypeData(
                            cuisineObject.getString(AppConstants.SAHALATH_JSON_TAG_CUISINE_NAME),
                            AppConstants.SAHALATH_BASE_THUMB_URL + cuisineObject.getString(AppConstants.SAHALATH_JSON_TAG_CUISINE_IMAGE_URL),
                            cuisineObject.getString(AppConstants.SAHALATH_JSON_TAG_CUISINE_ID),
                            placeDataList);
                    searchFoodTypeDatas.add(searchFoodTypeData);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            addItemsToSearch();
        }


    }

    @Override
    public void onPause() {
        // code run before super
        super.onPause(); // <-- the super
        // code run after super
    }

    /*@Override
    public void onDestroyView() {
        try{
            SupportMapFragment fragment = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.frame_outer_search));
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }catch(Exception e){
        }

        super.onDestroyView();
    }*/

    @Override
    public void onHomePageDetailsFailed(JSONObject jsonObject) {
        super.onHomePageDetailsFailed(jsonObject);
        Activity.stopThemeSpinWheel();

    }

    public void addItemsToOuterSearch(ArrayList<RestaurantPlaceData> restaurantPlaceDatas) {
        while (circleLayoutOuter.getChildCount() > 0) {
            circleLayoutOuter.removeViewAt(circleLayoutOuter.getChildCount() - 1);
            Log.e(TAG, "remoview View" + circleLayoutOuter.getChildCount());
        }
        for (int k = 0; k < restaurantPlaceDatas.size(); k++) {
            TextViewWithId textViewWithId = new TextViewWithId(getContext(), null);
            String c;
            textViewWithId.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/RobotoCondensed-Regular.ttf"));
            if (restaurantPlaceDatas.get(k).placeName.length() > 9) {
                c = restaurantPlaceDatas.get(k).placeName;
                c = c.substring(0, 8) + "\n" + c.substring(8, c.length());
                Log.e(TAG, "sttring>>>>." + c);
                textViewWithId.setText(c);
            } else {
                textViewWithId.setText(restaurantPlaceDatas.get(k).placeName);
            }
            textViewWithId.setTextColor(Color.WHITE);
            textViewWithId.setTextId(restaurantPlaceDatas.get(k).placeId);
            circleLayoutOuter.addView(textViewWithId);
        }
    }

    @Override
    public void onItemSelected(View view) {
        final int arraylocation;
        if (view instanceof TitiledImageView) {
            arraylocation = ((TitiledImageView) view).getArrayLocation();

        } else {
            arraylocation = 0;
        }
        addItemsToOuterSearch(searchFoodTypeDatas.get(arraylocation).placeData);
        circleLayoutOuter.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.BounceIn).duration(1600).playOn(circleLayoutOuter);
        Log.e("ItemSelected", view.getContentDescription().toString());
        selectedInnerId = view.getContentDescription().toString();
    }

    @Override
    public void onOuterItemSelected(View view) {
        final String locationId;
        if (view instanceof TextViewWithId) {
            locationId = ((TextViewWithId) view).getTextId();
        } else {
            locationId = null;
        }
        Log.e(TAG, "OuterItem>>>>" + locationId);
        selectedOuterId = locationId;
    }
}

