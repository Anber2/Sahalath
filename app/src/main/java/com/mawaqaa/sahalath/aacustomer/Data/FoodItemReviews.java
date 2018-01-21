package com.mawaqaa.sahalath.aacustomer.Data;

import java.util.ArrayList;

/**
 * Created by anson on 3/27/2017.
 */

public class FoodItemReviews {
    public String foodItemName, foodItemAveregeRating, noOfRatings, noOfReviews, countFiveRatings,
            countFourRatings, countThreeRatings, countTwoRatings, countOneRatings;
    ArrayList<ReviewData> reviewDatas;

    public FoodItemReviews(String foodItemName, String foodItemAveregeRating, String noOfRatings,
                           String noOfReviews, String countFiveRatings, String countFourRatings, String countThreeRatings,
                           String countTwoRatings, String countOneRatings) {
        this.foodItemName = foodItemName;
        this.foodItemAveregeRating = foodItemAveregeRating;
        this.noOfRatings = noOfRatings;
        this.noOfReviews = noOfReviews;
        this.countFiveRatings = countFiveRatings;
        this.countFourRatings = countFourRatings;
        this.countThreeRatings = countThreeRatings;
        this.countTwoRatings = countTwoRatings;
        this.countOneRatings = countOneRatings;

    }

    public FoodItemReviews(String foodItemName, String foodItemAveregeRating, String noOfRatings,
                           String noOfReviews, String countFiveRatings, String countFourRatings, String countThreeRatings,
                           String countTwoRatings, String countOneRatings, ArrayList<ReviewData> reviewDatas) {
        this.foodItemName = foodItemName;
        this.foodItemAveregeRating = foodItemAveregeRating;
        this.noOfRatings = noOfRatings;
        this.noOfReviews = noOfReviews;
        this.countFiveRatings = countFiveRatings;
        this.countFourRatings = countFourRatings;
        this.countThreeRatings = countThreeRatings;
        this.countTwoRatings = countTwoRatings;
        this.countOneRatings = countOneRatings;
        this.reviewDatas = reviewDatas;
    }

    public class ReviewData {
        public String reviewHeading, reviewDescription, reviewRating, reviewwdPerson, reviewDate, noOfLikes, noOfDisLikes, reviewId;

        public ReviewData(String reviewHeading, String reviewDescription, String reviewRating, String reviewwdPerson, String reviewDate,
                          String noOfLikes, String noOfDisLikes, String reviewId) {
            this.noOfDisLikes = noOfDisLikes;
            this.noOfLikes = noOfLikes;
            this.reviewDate = reviewDate;
            this.reviewDescription = reviewDescription;
            this.reviewHeading = reviewHeading;
            this.reviewId = reviewId;
            this.reviewRating = reviewRating;
            this.reviewwdPerson = reviewwdPerson;
        }


    }
}
