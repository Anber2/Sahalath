package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData;
import com.mawaqaa.sahalath.aacustomer.adapters.RestaurantMainMenuAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 3/7/2017.
 */

public class RestaurantMenuListFragment extends SahalathBaseFragment implements View.OnClickListener {
    ArrayList<RestaurantData.RestaurantMenu> restaurantMenuArrayList;
    ListView listViewMenuCat;
    SelectableRoundedImageView restFeaturedImage, restLogo;
    TextView tvRestName, tvRestAddress, tvRestReviewCount;
    RatingBar ratingBarRest;
    Button buttonViewReviews;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showUpButton();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_rest_menu_list_details_page, container, false);
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        listViewMenuCat = (ListView) v.findViewById(R.id.list_view_restaurant_menu);
        tvRestName = (TextView) v.findViewById(R.id.tv_rest_details_name);
        tvRestAddress = (TextView) v.findViewById(R.id.tv_rest_details_address);
        tvRestReviewCount = (TextView) v.findViewById(R.id.tv_rest_details_rating_count);
        ratingBarRest = (RatingBar) v.findViewById(R.id.rest_details_rating);
        restFeaturedImage = (SelectableRoundedImageView) v.findViewById(R.id.img_restaurant_featured);
        restLogo = (SelectableRoundedImageView) v.findViewById(R.id.img_restaurant_logo);
        buttonViewReviews = (Button) v.findViewById(R.id.btn_rest_view_reviews);
        loadFoodCategories();
        buttonViewReviews.setOnClickListener(this);
    }

    private void loadFoodCategories() {
        RestaurantMainMenuAdapter adapter = new RestaurantMainMenuAdapter(Activity, restaurantMenuArrayList);
        listViewMenuCat.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rest_view_reviews:
                Fragment reviewsPage = new RestaurantReviewsFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(reviewsPage, true, true);
                break;
            default:
                break;
        }
    }
}
