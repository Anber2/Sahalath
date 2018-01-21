package com.mawaqaa.sahalath.aadriver.data;

import java.io.Serializable;

/**
 * Created by anson on 3/15/2017.
 */

public class DriverOrderData implements Serializable {
    public String orderId, restaurantName, restaurantLocation, customerName, telephoneNumber, customerLocaton, customerBuilding, customerBlock, customerStreet, deliverTime = "";

    public DriverOrderData(String orderId, String restaurantName, String restaurantLocation,
                           String customerName, String telephoneNumber, String customerLocaton,
                           String customerBuilding, String customerBlock, String customerStreet, String deliverTime) {
        this.orderId = orderId;
        this.restaurantName = restaurantName;
        this.customerLocaton = customerLocaton;
        this.restaurantLocation = restaurantLocation;
        this.customerName = customerName;
        this.telephoneNumber = telephoneNumber;
        this.customerBuilding = customerBuilding;
        this.customerBlock = customerBlock;
        this.customerStreet = customerStreet;
        this.deliverTime = deliverTime;
    }
}
