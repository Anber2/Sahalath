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
import com.mawaqaa.sahalath.aacustomer.adapters.CartItemAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 3/10/2017.
 */

public class MyCartFragment extends SahalathBaseFragment {
    private String TAG="MyCartFragment";
    public SahalathBaseActivity Activity;
    ListView listViewCart;
    ArrayList<RestaurantData.FoodDetails> foodDetailsArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_my_cart, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        loadCartItems();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadCartItems() {
        CartItemAdapter adapter = new CartItemAdapter(Activity, foodDetailsArrayList);
        listViewCart.setAdapter(adapter);
    }

    private void initView(View v) {
        listViewCart = (ListView) v.findViewById(R.id.list_view_cart);
    }


}
