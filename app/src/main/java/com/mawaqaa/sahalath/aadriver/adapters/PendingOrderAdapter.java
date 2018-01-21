package com.mawaqaa.sahalath.aadriver.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aadriver.data.DriverOrderData;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 3/15/2017.
 */

public class PendingOrderAdapter extends BaseAdapter {
    ArrayList<DriverOrderData> orderDatas;
    private Activity activity;
    View rowView;

    public PendingOrderAdapter(Activity activity, ArrayList<DriverOrderData> orderDatas) {
        //super(activity, R.layout.single_driver_pending_order, orderDatas);
        this.orderDatas = orderDatas;
        this.activity = activity;
    }

    static class PendingOrderViewHolder {
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
        orderDatas.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        rowView = convertView;
        PendingOrderViewHolder viewHolder;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.single_driver_pending_order, null);

            viewHolder = new PendingOrderViewHolder();
            viewHolder.relativeLayout = (RelativeLayout) rowView.findViewById(R.id.rl_pending_order);
            viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.check_box_pending_order_item);
            viewHolder.textViewOrderId = (TextView) rowView.findViewById(R.id.tv_pending_order_id_value);
            viewHolder.textViewRestaurantName = (TextView) rowView.findViewById(R.id.tv_pending_order_rest_name_value);
            viewHolder.textViewRestaurantLocation = (TextView) rowView.findViewById(R.id.tv_pending_order_rest_location_value);
            viewHolder.textViewCustomerName = (TextView) rowView.findViewById(R.id.tv_pending_order_cst_name_value);
            viewHolder.textViewCustomerLocation = (TextView) rowView.findViewById(R.id.tv_pending_order_cst_location_value);
            viewHolder.textViewCustomerPhone = (TextView) rowView.findViewById(R.id.tv_pending_order_cst_telephone_value);

            viewHolder.textViewOrderId.setText(orderDatas.get(position).orderId);
            viewHolder.textViewRestaurantName.setText(orderDatas.get(position).restaurantName);
            viewHolder.textViewRestaurantLocation.setText(orderDatas.get(position).restaurantLocation);
            viewHolder.textViewCustomerName.setText(orderDatas.get(position).customerName);
            viewHolder.textViewCustomerLocation.setText(orderDatas.get(position).customerLocaton);
            viewHolder.textViewCustomerPhone.setText(orderDatas.get(position).telephoneNumber);

            rowView.setTag(viewHolder);


            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(b == true){
                        PendingOrderViewHolder viewHolder = (PendingOrderViewHolder) rowView.getTag();
                        AppConstants.ORDER_ID.add(Integer.valueOf(viewHolder.textViewOrderId.getText().toString()));
                    }else{
                        PendingOrderViewHolder viewHolder = (PendingOrderViewHolder) rowView.getTag();
                     //   Object mValueItem = Integer.valueOf(viewHolder.textViewOrderId.getText().toString());
                        AppConstants.ORDER_ID.remove(position);
                    }
                }
            });

        }




//        PendingOrderViewHolder holder = (PendingOrderViewHolder) rowView.getTag();
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment orderFragment = new PendingOrderDetailsFragments();
//                SahalathMainActivity.getSahalathBaseActivity().pushFragments(orderFragment, true, true);
//            }
//        });
        return rowView;
    }
}
