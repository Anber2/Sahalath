package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData;
import com.mawaqaa.sahalath.aacustomer.adapters.FoodItemListAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.interfaces.BottomBarHandlingUtility;

import java.util.ArrayList;

/**
 * Created by anson on 3/9/2017.
 */

public class MostSellingDishesFragments extends SahalathBaseFragment {
    private String TAG = "MostSellingDishesFragments";
    public SahalathBaseActivity Activity;
    ListView mostSellingRestListView;
    ArrayList<RestaurantData.FoodDetails> foodDetailsArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_most_selling_dish, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        loadRestaurantList();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        mostSellingRestListView = (ListView) v.findViewById(R.id.most_selling_rest_list);

    }
    private void loadRestaurantList() {
        FoodItemListAdapter adapter = new FoodItemListAdapter(Activity, foodDetailsArrayList);
        mostSellingRestListView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((SahalathBaseActivity) getActivity()).BaseFragment = this;
        BottomBarHandlingUtility bottomBarHandlingUtility = (BottomBarHandlingUtility) Activity;
        bottomBarHandlingUtility.mostSellingDishPage();
    }
}
