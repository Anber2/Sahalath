package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData;
import com.mawaqaa.sahalath.aacustomer.adapters.CartItemAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

public class OffersFragment extends Fragment {
    private String TAG="OffersFragment";
    public SahalathBaseActivity Activity;
    ListView listViewOffers;
    ArrayList<RestaurantData.FoodDetails> foodDetailsArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    public OffersFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_offers, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        loadCartItems();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadCartItems() {
        CartItemAdapter adapter = new CartItemAdapter(Activity, foodDetailsArrayList);
        listViewOffers.setAdapter(adapter);
    }

    private void initView(View v) {
        listViewOffers = (ListView) v.findViewById(R.id.list_view_offers);
    }

}
