package com.mawaqaa.sahalath.aacustomer.Data;

/**
 * Created by anson on 4/13/2017.
 */

public class UserData {
    public String userId, userName, userFirstName, userLastName, userEmailId, userCountryId, userAreaId, userMobile;
    public int userType;

    public UserData(String userId, String userName, String userFirstName, String userLastName, String userEmailId,
                    String userCountryId, String userAreaId, String userMobile, int userType) {
        this.userId = userId;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmailId = userEmailId;
        this.userCountryId = userCountryId;
        this.userAreaId = userAreaId;
        this.userMobile = userMobile;
        this.userType = userType;
    }
}
