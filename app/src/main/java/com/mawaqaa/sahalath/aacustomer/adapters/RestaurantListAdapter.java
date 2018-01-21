package com.mawaqaa.sahalath.aacustomer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData;
import com.mawaqaa.sahalath.aacustomer.fragments.RestaurantMenuListFragment;

import java.util.ArrayList;

/**
 * Created by anson on 3/7/2017.
 */

public class RestaurantListAdapter extends ArrayAdapter<RestaurantData> {
    String TAG ="RestaurantListAdapter";
    private Activity activity;
    private ArrayList<RestaurantData> restaurantDataArrayList;

    public RestaurantListAdapter(Activity context, ArrayList<RestaurantData> restaurantDataArrayList) {
        super(context, R.layout.single_customer_rest_list_item, restaurantDataArrayList);
        this.activity = context;
        this.restaurantDataArrayList = restaurantDataArrayList;

    }

    static class RestaurantListViewHolder {
        public TextView tvRestName, tvRestAddress, tvRestDistance;
        public SelectableRoundedImageView imgViewRestLogo;
        public RatingBar ratingBar;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.single_customer_rest_list_item, null);

            RestaurantListViewHolder viewHolder = new RestaurantListViewHolder();

            viewHolder.tvRestName = (TextView) rowView.findViewById(R.id.tv_restaurant_title_name);
            viewHolder.tvRestAddress = (TextView) rowView.findViewById(R.id.tv_restaurant_address);
            viewHolder.tvRestDistance = (TextView) rowView.findViewById(R.id.tv_restaurant_distance);
            viewHolder.imgViewRestLogo = (SelectableRoundedImageView) rowView.findViewById(R.id.srimgview_restaurant_logo);
            viewHolder.ratingBar = (RatingBar) rowView.findViewById(R.id.rest_rating);
            rowView.setTag(viewHolder);
        }
        RestaurantListViewHolder holder = (RestaurantListViewHolder) rowView.getTag();

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RestaurantMenuListFragment();
                SahalathBaseActivity.getSahalathBaseActivity().pushFragments(fragment, true, true);
            }
        });
        return rowView;
    }
}
