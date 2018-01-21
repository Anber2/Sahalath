package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 4/10/2017.
 */

public class RestaurantReviewSubmitFragment extends SahalathBaseFragment {
    private String TAG = "RestaurantReviewSubmitFragment";
    public SahalathBaseActivity Activity;
    TextView tvRestName, tvRestAddress, tvRestReviewCount;
    SelectableRoundedImageView restFeaturedImage, restLogo;
    RatingBar ratingBarRest;
    EditText editTextReview;
    Button buttonViewReviews, buttonReset, buttonSubmit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_submit_rest_review, container,
                false);
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        tvRestName = (TextView) v.findViewById(R.id.tv_rest_details_name);
        tvRestAddress = (TextView) v.findViewById(R.id.tv_rest_details_address);
        tvRestReviewCount = (TextView) v.findViewById(R.id.tv_rest_details_rating_count);
        ratingBarRest = (RatingBar) v.findViewById(R.id.rest_details_rating);
        restFeaturedImage = (SelectableRoundedImageView) v.findViewById(R.id.img_restaurant_featured);
        restLogo = (SelectableRoundedImageView) v.findViewById(R.id.img_restaurant_logo);
        buttonViewReviews = (Button) v.findViewById(R.id.btn_rest_view_reviews);
        editTextReview = (EditText) v.findViewById(R.id.et_submit_rest_review);
        buttonSubmit = (Button) v.findViewById(R.id.btn_rest_review_submit);
        buttonReset = (Button) v.findViewById(R.id.btn_rest_review_cancel);
        buttonViewReviews.setVisibility(View.GONE);
        tvRestReviewCount.setVisibility(View.GONE);
    }
}