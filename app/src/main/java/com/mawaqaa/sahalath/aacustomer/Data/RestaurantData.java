package com.mawaqaa.sahalath.aacustomer.Data;

/**
 * Created by anson on 3/7/2017.
 */

public class RestaurantData {
    public String restName, restId, restAddress, restDistanceToCustomer, restLogo;
    public float restRating;

    public RestaurantData(String restName, String restId, String restAddress, String restDistanceToCustomer,
                          String restLogo, float restRating) {
        this.restId = restId;
        this.restName = restName;
        this.restAddress = restAddress;
        this.restDistanceToCustomer = restDistanceToCustomer;
        this.restLogo = restLogo;
        this.restRating = restRating;
    }

    public class RestaurantMenu {
        public String menuCategoryName, menuItems, menuCategoryId, menuId;

        public RestaurantMenu(String menuCategoryName, String menuItems, String menuCategoryId, String menuId) {
            this.menuCategoryId = menuCategoryId;
            this.menuCategoryName = menuCategoryName;
            this.menuItems = menuItems;
            this.menuId = menuId;
        }
    }

    public class FoodDetails {
        String foodName, foodPrice, qtyCount, foodItemId;

        public FoodDetails(String foodName, String foodPrice, String foodItemId) {
            this.foodName = foodName;
            this.foodPrice = foodPrice;
            this.foodItemId = foodItemId;
        }

        public FoodDetails(String foodName, String foodPrice, String qtyCount, String foodItemId) {
            this.foodName = foodName;
            this.foodPrice = foodPrice;
            this.qtyCount = qtyCount;
            this.foodItemId = foodItemId;
        }
    }

}
