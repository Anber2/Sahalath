package com.mawaqaa.sahalath.aacustomer.Data;

/**
 * Created by anson on 3/13/2017.
 */

public class OrderData {
    public String orderId, orderedFoodItemName, orderPrice, orderDate, orderStatus;

    public OrderData(String orderId, String orderedFoodItemName, String orderPrice, String orderDate, String orderStatus) {
        this.orderId = orderId;
        this.orderedFoodItemName = orderedFoodItemName;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

}
