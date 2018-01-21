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
import com.mawaqaa.sahalath.aacustomer.Data.FoodItemReviews;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.customviews.MagicProgressBar;
import com.mawaqaa.sahalath.customviews.interfaces.ISmoothTarget;

import java.util.ArrayList;

/**
 * Created by anson on 3/24/2017.
 */

public class FoodItemReviewFragment extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "FoodItemReviewFragment";
    public SahalathBaseActivity Activity;
    SelectableRoundedImageView foodImageView;
    TextView textViewFoodName, textViewRatingCount, textViewAverageRating, textViewReviewCount;
    RatingBar foodReviewRatingBar;
    Button buttonWriteReview;
    ArrayList<FoodItemReviews> reviewDatas;
    MagicProgressBar progressBarOne, progressBarTwo, progressBarThree, progressBarFour,
            progressBarFive;
    LinearLayout listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_food_item_review, container,
                false);
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private float getIncreasedPercent(ISmoothTarget target) {
        float increasedPercent = target.getPercent() + 0.1f;
        return Math.min(1, increasedPercent);
    }

    private void initView(View v) {
        textViewFoodName = (TextView) v.findViewById(R.id.tv_food_review_header_food_name);
        textViewRatingCount = (TextView) v.findViewById(R.id.tv_food_review_header_rating_count);
        foodImageView = (SelectableRoundedImageView) v.findViewById(R.id.img_food_review_header);
        foodReviewRatingBar = (RatingBar) v.findViewById(R.id.food_review_header_rating);
        textViewReviewCount = (TextView) v.findViewById(R.id.tv_review_count);
        textViewAverageRating = (TextView) v.findViewById(R.id.tv_average_rating_value);
        buttonWriteReview = (Button) v.findViewById(R.id.btn_write_review);
        progressBarOne = (MagicProgressBar) v.findViewById(R.id.mpb_one);
        progressBarTwo = (MagicProgressBar) v.findViewById(R.id.mpb_two);
        progressBarThree = (MagicProgressBar) v.findViewById(R.id.mpb_three);
        progressBarFour = (MagicProgressBar) v.findViewById(R.id.mpb_four);
        progressBarFive = (MagicProgressBar) v.findViewById(R.id.mpb_five);

        listView = (LinearLayout) v.findViewById(R.id.food_item_review_list_view);
        buttonWriteReview.setOnClickListener(this);
        addREviews(listView);
        progressBarOne.setSmoothPercent(20 / 100f);
        progressBarTwo.setSmoothPercent(35 / 100f);
        progressBarThree.setSmoothPercent(50 / 100f);
        progressBarFour.setSmoothPercent(80 / 100f);
        progressBarFive.setSmoothPercent(45 / 100f);
    }

    private void addREviews(LinearLayout listView) {
        for (int i = 0; i <= 8; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.single_customer_food_item_review, listView, false);
            listView.addView(view);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_write_review:
                Fragment fragment = new FoodReviewSubmitFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(fragment, true, true);
                break;
            default:
                break;
        }
    }
}
