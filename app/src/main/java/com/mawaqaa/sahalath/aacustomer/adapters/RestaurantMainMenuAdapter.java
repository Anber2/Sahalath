package com.mawaqaa.sahalath.aacustomer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData;
import com.mawaqaa.sahalath.aacustomer.fragments.FoodItemListPageFragment;

import java.util.ArrayList;

/**
 * Created by anson on 3/7/2017.
 */

public class RestaurantMainMenuAdapter extends ArrayAdapter<RestaurantData.RestaurantMenu> {
    ArrayList<RestaurantData.RestaurantMenu> restaurantMenuArrayList;
    Activity activity;

    public RestaurantMainMenuAdapter(Activity context, ArrayList<RestaurantData.RestaurantMenu> restaurantMenus) {
        super(context, R.layout.single_customer_rest_list_item, restaurantMenus);
        this.activity = context;
        this.restaurantMenuArrayList = restaurantMenus;
    }

    static class MainMenuViewHolder {
        public SelectableRoundedImageView imgViewRestLogo;
        public TextView tvMainMenuTItle, tvMenuContents, btnViewAll;

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
            rowView = inflater.inflate(R.layout.single_customer_rest_menu_list_item, null);
            MainMenuViewHolder viewHolder = new MainMenuViewHolder();
            viewHolder.tvMainMenuTItle = (TextView) rowView.findViewById(R.id.tv_rest_menu_cat_name);
            viewHolder.imgViewRestLogo = (SelectableRoundedImageView) rowView.findViewById(R.id.img_rest_menu_cat);
            viewHolder.tvMenuContents = (TextView) rowView.findViewById(R.id.tv_rest_menu_items_in_cat);
            viewHolder.btnViewAll = (TextView) rowView.findViewById(R.id.btn_list_all_menu);
            viewHolder.btnViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new FoodItemListPageFragment();
                    SahalathMainActivity.getSahalathBaseActivity().pushFragments(fragment, true, true);
                }
            });
            rowView.setTag(viewHolder);
        }
        MainMenuViewHolder holder = (MainMenuViewHolder) rowView.getTag();

        return rowView;
    }
}
