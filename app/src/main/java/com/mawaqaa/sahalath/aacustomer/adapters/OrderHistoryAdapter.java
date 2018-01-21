package com.mawaqaa.sahalath.aacustomer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.OrderData;
import com.mawaqaa.sahalath.aacustomer.fragments.TrackYourOrdersFragment;

import java.util.ArrayList;

/**
 * Created by anson on 3/13/2017.
 */

public class OrderHistoryAdapter extends ArrayAdapter<OrderData> {
    private String TAG = "OrderHistoryAdapter";
    private Activity activity;
    ArrayList<OrderData> orderDatas;

    public OrderHistoryAdapter(Activity activity, ArrayList<OrderData> orderDatas) {
        super(activity, R.layout.single_customer_order_item, orderDatas);
        this.activity = activity;
        this.orderDatas = orderDatas;
    }

    static class OrderItemViewHolder {

    }

    @Override
    public int getCount() {
        return 8;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.single_customer_order_item, null);

            OrderItemViewHolder viewHolder = new OrderItemViewHolder();

            rowView.setTag(viewHolder);
        }
        OrderItemViewHolder holder = (OrderItemViewHolder) rowView.getTag();

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TrackYourOrdersFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(fragment, true, true);
            }
        });

        return rowView;
    }
}
