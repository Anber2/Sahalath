package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 3/23/2017.
 */

public class FoodItemDetailsPageFragment extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "FoodItemDetailsFragment";
    public SahalathBaseActivity Activity;
    ImageButton imageButtonGalleryMore;
    RatingBar ratingBarFoodItem;
    SelectableRoundedImageView imageViewProductFeartured, imageViewGallery1,
            imageViewGallery2, imageViewGallery3, imageViewGallery4, imageViewGallery5;
    Button buttonPlus, buttonMinus, buttonViewReviews, buttonOrderNow, buttonAddToCart;
    TextView textViewFoodItemName, textViewReviewCount, textViewFoodItemDescription,
            textViewFoodPrice, textViewCartCount;

    static int minteger = 01;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_food_details_page, container,
                false);
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        imageViewProductFeartured = (SelectableRoundedImageView) v.findViewById(R.id.img_food_details);
        imageViewGallery1 = (SelectableRoundedImageView) v.findViewById(R.id.food_item_img_one);
        imageViewGallery2 = (SelectableRoundedImageView) v.findViewById(R.id.food_item_img_two);
        imageViewGallery3 = (SelectableRoundedImageView) v.findViewById(R.id.food_item_img_three);
        imageViewGallery4 = (SelectableRoundedImageView) v.findViewById(R.id.food_item_img_four);
        imageViewGallery5 = (SelectableRoundedImageView) v.findViewById(R.id.food_item_img_five);
        imageButtonGalleryMore = (ImageButton) v.findViewById(R.id.btn_gallery_more);
        buttonPlus = (Button) v.findViewById(R.id.btn_food_item_plus);
        buttonMinus = (Button) v.findViewById(R.id.btn_food_item_minus);
        buttonViewReviews = (Button) v.findViewById(R.id.btn_view_reviews);
        buttonAddToCart = (Button) v.findViewById(R.id.btn_food_item_add_to_cart);
        buttonOrderNow = (Button) v.findViewById(R.id.btn_food_item_order);
        ratingBarFoodItem = (RatingBar) v.findViewById(R.id.food_item_rating);
        textViewFoodItemName = (TextView) v.findViewById(R.id.tv_food_review_header_food_name);
        textViewReviewCount = (TextView) v.findViewById(R.id.tv_food__rating_count);
        textViewFoodItemDescription = (TextView) v.findViewById(R.id.tv_food_item_description);
        textViewFoodPrice = (TextView) v.findViewById(R.id.tv_food_details_price);
        textViewCartCount = (TextView) v.findViewById(R.id.tv_food_item_count);

        imageViewGallery1.setOnClickListener(this);
        imageViewGallery2.setOnClickListener(this);
        imageViewGallery3.setOnClickListener(this);
        imageViewGallery4.setOnClickListener(this);
        imageButtonGalleryMore.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonViewReviews.setOnClickListener(this);
        buttonAddToCart.setOnClickListener(this);
        buttonOrderNow.setOnClickListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        SahalathMainActivity.showBottomBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_food_item_add_to_cart:
                Log.e(TAG, "Add toCart");
                break;
            case R.id.btn_food_item_order:
                Log.e(TAG, "Order Now");
                break;
            case R.id.food_item_img_one:
                break;
            case R.id.food_item_img_two:
                break;
            case R.id.food_item_img_three:
                break;
            case R.id.food_item_img_four:
                break;
            case R.id.food_item_img_five:
                break;
            case R.id.btn_view_reviews:
                Fragment fragment = new FoodItemReviewFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(fragment, true, true);
                break;
            case R.id.btn_food_item_plus:
                minteger = minteger + 1;
                textViewCartCount.setText("" + minteger);
                break;
            case R.id.btn_food_item_minus:
                if(minteger>=1){
                minteger = minteger - 1;}
                textViewCartCount.setText("" + minteger);
                break;
            case R.id.btn_gallery_more:
                Fragment galleryListFragment = new FoodGalleryListFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(galleryListFragment, true, true);
                break;
            default:
                break;
        }
    }
}
