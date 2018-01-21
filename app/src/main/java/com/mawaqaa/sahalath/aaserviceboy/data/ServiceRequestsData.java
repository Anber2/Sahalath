package com.mawaqaa.sahalath.aaserviceboy.data;

/**
 * Created by anson on 3/15/2017.
 */

public class ServiceRequestsData {
    public String serviceID, driverName, telephoneNumber, location, issueType, issueMessage;

    public ServiceRequestsData(String serviceID, String driverName, String telephoneNumber, String location,
                               String issueType, String issueMessage) {
        this.serviceID = serviceID;
        this.driverName = driverName;
        this.telephoneNumber = telephoneNumber;
        this.location = location;
        this.issueType = issueType;
        this.issueMessage = issueMessage;
    }

    public ServiceRequestsData(String serviceID, String driverName, String location, String telephoneNumber) {
        this.serviceID = serviceID;
        this.driverName = driverName;
        this.location = location;
        this.telephoneNumber = telephoneNumber;

    }


}
