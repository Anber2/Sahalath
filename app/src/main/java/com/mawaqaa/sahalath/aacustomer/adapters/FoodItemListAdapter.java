package com.mawaqaa.sahalath.aacustomer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aacustomer.Data.RestaurantData.FoodDetails;
import com.mawaqaa.sahalath.aacustomer.fragments.FoodItemDetailsPageFragment;

import java.util.ArrayList;

/**
 * Created by anson on 3/8/2017.
 */

public class FoodItemListAdapter extends ArrayAdapter<FoodDetails> {
    private String TAG = "FoodItemListAdapter";
    private Activity activity;
    ArrayList<FoodDetails> foodDetailsArrayList;

    public FoodItemListAdapter(Activity activity, ArrayList<FoodDetails> foodDetailses) {
        super(activity, R.layout.single_customer_food_list_item, foodDetailses);
        this.activity = activity;
        this.foodDetailsArrayList = foodDetailses;

    }

    static class FoodItemViewHolder {
        RelativeLayout rlBody;
        public TextView tvFoodTitle, tvFoodPrice;
        public SelectableRoundedImageView imgFoodItem;
        public Spinner spinnerQuantity;
        public Button btnAddToCart;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG,""+position);
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.single_customer_food_list_item, null);

            FoodItemViewHolder viewHolder = new FoodItemViewHolder();
            viewHolder.rlBody = (RelativeLayout) rowView.findViewById(R.id.rl_single_food_item);
            viewHolder.tvFoodTitle = (TextView) rowView.findViewById(R.id.tv_rest_food_item_name);
            viewHolder.tvFoodPrice = (TextView) rowView.findViewById(R.id.tv_rest_food_item_price);
            viewHolder.imgFoodItem = (SelectableRoundedImageView) rowView.findViewById(R.id.img_rest_food_item);
            viewHolder.spinnerQuantity = (Spinner) rowView.findViewById(R.id.spinner_food_item_quantity);
            viewHolder.btnAddToCart = (Button) rowView.findViewById(R.id.btn_add_to_cart);
            rowView.setTag(viewHolder);
        }
        int spinnerCount = 20;
        int[] quantityArray = new int[spinnerCount + 1];
        for (int i = 0; i < (spinnerCount + 1); i++) {
            quantityArray[i] = i;
        }
        FoodItemViewHolder holder = (FoodItemViewHolder) rowView.getTag();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.imgFoodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FoodItemDetailsPageFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(fragment, true, true);
            }
        });
        holder.spinnerQuantity.setAdapter(adapter);
        return rowView;
    }
}
