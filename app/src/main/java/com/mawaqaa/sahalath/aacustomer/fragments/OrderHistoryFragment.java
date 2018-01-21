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
import com.mawaqaa.sahalath.aacustomer.Data.OrderData;
import com.mawaqaa.sahalath.aacustomer.adapters.OrderHistoryAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 3/13/2017.
 */

public class OrderHistoryFragment extends SahalathBaseFragment {
    private String TAG = "OrderHistoryFragment";
    public SahalathBaseActivity Activity;
    ListView listViewOrderHistory;
    ArrayList<OrderData> orderDatas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_history, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        loadOrderItems();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadOrderItems() {
        OrderHistoryAdapter adapter = new OrderHistoryAdapter(Activity, orderDatas);
        listViewOrderHistory.setAdapter(adapter);
    }

    private void initView(View v) {
        listViewOrderHistory = (ListView) v.findViewById(R.id.list_view_order_history);
    }
}
