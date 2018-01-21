package com.mawaqaa.sahalath.aadriver.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aadriver.data.DriverOrderData;

import java.util.ArrayList;

/**
 * Created by anson on 3/15/2017.
 */

public class AcceptedOrderAdapter extends BaseAdapter {
    ArrayList<DriverOrderData> orderDatas;
    private Activity activity;


    public AcceptedOrderAdapter(Activity activity, ArrayList<DriverOrderData> orderDatas) {
      //  super(activity, R.layout.single_driver_pending_order, orderDatas);
        this.orderDatas = orderDatas;
        this.activity = activity;
    }

    static class AcceptedOrderViewHolder {
        CheckBox checkBox;
        RelativeLayout relativeLayout;
        TextView textViewOrderId, textViewRestaurantName, textViewRestaurantLocation, textViewCustomerName,
                textViewCustomerLocation, textViewCustomerPhone;
    }

    @Override
    public int getCount() {
        return orderDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return orderDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.single_driver_accepted_order, null);

            AcceptedOrderViewHolder viewHolder = new AcceptedOrderViewHolder();
            viewHolder.relativeLayout = (RelativeLayout) rowView.findViewById(R.id.rl_accepted_order);

            viewHolder.textViewOrderId = (TextView) rowView.findViewById(R.id.tv_accepted_order_id_value);
            viewHolder.textViewRestaurantName = (TextView) rowView.findViewById(R.id.tv_accepted_order_rest_name_value);
            viewHolder.textViewRestaurantLocation = (TextView) rowView.findViewById(R.id.tv_accepted_order_rest_location_value);
            viewHolder.textViewCustomerName = (TextView) rowView.findViewById(R.id.tv_accepted_order_cst_name_value);
            viewHolder.textViewCustomerLocation = (TextView) rowView.findViewById(R.id.tv_accepted_order_cst_location_value);
            viewHolder.textViewCustomerPhone = (TextView) rowView.findViewById(R.id.tv_accepted_order_cst_telephone_value);

            viewHolder.textViewOrderId.setText(orderDatas.get(position).orderId);
            viewHolder.textViewRestaurantName.setText(orderDatas.get(position).restaurantName);
            viewHolder.textViewRestaurantLocation.setText(orderDatas.get(position).restaurantLocation);
            viewHolder.textViewCustomerName.setText(orderDatas.get(position).customerName);
            viewHolder.textViewCustomerLocation.setText(orderDatas.get(position).customerLocaton);
            viewHolder.textViewCustomerPhone.setText(orderDatas.get(position).telephoneNumber);




            rowView.setTag(position);
        }



//        AcceptedOrderViewHolder holder = (AcceptedOrderViewHolder) rowView.getTag();
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment orderFragment = new OrderDetailsFragment();
//                SahalathMainActivity.getSahalathBaseActivity().pushFragments(orderFragment, true, true);
//            }
//        });
        return rowView;
    }
}
