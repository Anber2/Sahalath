package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.customviews.MagicProgressBar;

/**
 * Created by anson on 4/10/2017.
 */

public class RestaurantReviewsFragment extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "RestaurantReviewsFragment";
    SelectableRoundedImageView restFeaturedImage, restLogo;
    TextView tvRestName, tvRestAddress, tvRestReviewCount;
    RatingBar ratingBarRest;
    Button buttonViewReviews;
    TextView textViewRatingCount, textViewAverageRating, textViewReviewCount;
    MagicProgressBar progressBarOne, progressBarTwo, progressBarThree, progressBarFour,
            progressBarFive;
    Button buttonWriteReview;
    LinearLayout listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showUpButton();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_rest_review, container, false);
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
        textViewAverageRating = (TextView) v.findViewById(R.id.tv_rest_average_rating_value);
        textViewReviewCount = (TextView) v.findViewById(R.id.tv_rest_review_count);
        progressBarOne = (MagicProgressBar) v.findViewById(R.id.rest_mpb_one);
        progressBarTwo = (MagicProgressBar) v.findViewById(R.id.rest_mpb_two);
        progressBarThree = (MagicProgressBar) v.findViewById(R.id.rest_mpb_three);
        progressBarFour = (MagicProgressBar) v.findViewById(R.id.rest_mpb_four);
        progressBarFive = (MagicProgressBar) v.findViewById(R.id.rest_mpb_five);
        buttonWriteReview = (Button) v.findViewById(R.id.btn_write_rest_review);
        listView = (LinearLayout) v.findViewById(R.id.food_item_review_list_view);
        buttonWriteReview.setOnClickListener(this);
        addREviews(listView);
        buttonViewReviews.setVisibility(View.GONE);
        tvRestReviewCount.setVisibility(View.GONE);
        progressBarOne.setSmoothPercent(20 / 100f);
        progressBarTwo.setSmoothPercent(35 / 100f);
        progressBarThree.setSmoothPercent(50 / 100f);
        progressBarFour.setSmoothPercent(80 / 100f);
        progressBarFive.setSmoothPercent(45 / 100f);
    }

    private void addREviews(LinearLayout listView) {
        for (int i = 0; i <= 8; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.single_customer_rest_item_review, listView, false);
            listView.addView(view);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write_rest_review:
                Fragment submitReview = new RestaurantReviewSubmitFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(submitReview, true, true);
                break;
            default:
                break;
        }
    }
}