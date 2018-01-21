package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData;
import com.mawaqaa.sahalath.aacustomer.adapters.RestaurantListAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.interfaces.BottomBarHandlingUtility;

import java.util.ArrayList;

/**
 * Created by anson on 3/8/2017.
 */

public class NewRestListPageFragment extends SahalathBaseFragment {
    public SahalathBaseActivity Activity;
    ListView restListView;
    TextView tvCommonTitle;
    ArrayList<RestaurantData> restaurantDataArrayList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_restaurant_list, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        loadRestaurantList();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View view) {
        tvCommonTitle = (TextView) view.findViewById(R.id.tv_restaurant_common_title);
        restListView = (ListView) view.findViewById(R.id.rest_list);

        tvCommonTitle.setText(getResources().getString(R.string.new_restaurants));
    }

    private void loadRestaurantList() {
        RestaurantListAdapter adapter = new RestaurantListAdapter(Activity, restaurantDataArrayList);
        restListView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((SahalathBaseActivity) getActivity()).BaseFragment = this;
        BottomBarHandlingUtility bottomBarHandlingUtility = (BottomBarHandlingUtility) Activity;
        bottomBarHandlingUtility.newRestPage();
    }
}
