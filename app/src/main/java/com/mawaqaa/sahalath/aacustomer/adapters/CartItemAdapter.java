package com.mawaqaa.sahalath.aacustomer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData;

import java.util.ArrayList;

/**
 * Created by anson on 3/10/2017.
 */

public class CartItemAdapter extends ArrayAdapter<RestaurantData.FoodDetails> {
    private Activity activity;
    ArrayList<RestaurantData.FoodDetails> foodDetailsArrayList;

    public CartItemAdapter(Activity activity, ArrayList<RestaurantData.FoodDetails> foodDetailses) {
        super(activity, R.layout.single_cart_list_item, foodDetailses);
        this.activity = activity;
        this.foodDetailsArrayList = foodDetailses;
    }

    static class CartItemViewHolder {
        public TextView tvFoodTitle, tvFoodPrice;
        public SelectableRoundedImageView imgFoodItem;
        public Spinner spinnerQuantity;
        public Button btnRemove;
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
            rowView = inflater.inflate(R.layout.single_cart_list_item, parent, false);

            CartItemViewHolder viewHolder = new CartItemViewHolder();
            viewHolder.tvFoodTitle = (TextView) rowView.findViewById(R.id.tv_cart_food_name);
            viewHolder.tvFoodPrice = (TextView) rowView.findViewById(R.id.tv_cart_item_price);
            viewHolder.imgFoodItem = (SelectableRoundedImageView) rowView.findViewById(R.id.srimgview_cart_food);
            viewHolder.spinnerQuantity = (Spinner) rowView.findViewById(R.id.spinner_cart_count);
            viewHolder.btnRemove = (Button) rowView.findViewById(R.id.btn_remove);
            rowView.setTag(viewHolder);

        }
        CartItemAdapter.CartItemViewHolder holder = (CartItemAdapter.CartItemViewHolder) rowView.getTag();

        return rowView;
    }
}
