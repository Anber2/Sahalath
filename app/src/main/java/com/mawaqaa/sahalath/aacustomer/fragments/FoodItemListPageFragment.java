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
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData.FoodDetails;
import com.mawaqaa.sahalath.aacustomer.adapters.FoodItemListAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 3/8/2017.
 */

public class FoodItemListPageFragment extends SahalathBaseFragment {
    private String TAG="FoodItemListPageFragment";
    ListView listViewFoodItems;
    ArrayList<FoodDetails> foodDetailsArrayList;
    public SahalathBaseActivity Activity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_food_items_list_page, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        loadFoodItemsList();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadFoodItemsList() {
        FoodItemListAdapter adapter=new FoodItemListAdapter(Activity,foodDetailsArrayList);
        listViewFoodItems.setAdapter(adapter);
    }

    private void initView(View v) {
        listViewFoodItems=(ListView)v.findViewById(R.id.list_view_food_items);
    }
}
