package com.mawaqaa.sahalath.aacustomer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aacustomer.Data.FoodItemReviews;

import java.util.ArrayList;

/**
 * Created by anson on 3/27/2017.
 */

public class FoodReviewAdapter extends ArrayAdapter<FoodItemReviews> {
    private String TAG = "FoodReviewAdapter";
    private Activity activity;
    ArrayList<FoodItemReviews> reviewsArrayList;

    public FoodReviewAdapter(Activity activity, ArrayList<FoodItemReviews> itemReviewses) {
        super(activity, R.layout.single_customer_food_item_review);
        this.activity = activity;
        this.reviewsArrayList = itemReviewses;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG,position+"");
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.single_customer_food_item_review, null);

        }
        return rowView;
    }
}
