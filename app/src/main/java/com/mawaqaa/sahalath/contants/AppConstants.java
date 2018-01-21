package com.mawaqaa.sahalath.contants;

import android.location.Location;
import android.support.v4.app.Fragment;

import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;

import java.util.ArrayList;
//import com.roger.catloadinglibrary.CatLoadingView;

/**
 * Created by anson on 2/28/2017.
 */

public class AppConstants {
    public static final int USER_CUSTOMER = 1, USER_DRIVER = 2, USER_SERVICE_BOY = 3;
    public static final int RESULT_CODE_OK = 1, RESULT_CODE_FAILURE = -1;

    public static final String SAHALATH_ENGLISH = "en";
    public static final String SAHALATH_ARABIC = "ar";
    public static Fragment CURRENT_FRAGMENT = null;

    /**********************************************************
     * ************************* PREFERENCE VALUES  *******************
     ************************************************************/
    public static final String SAHALATH_PREF_LANGUAGE = "lang";
    public static final String SAHALATH_PREF_IS_LOGGED_IN = "isLoggedIn";
    public static final String SAHALATH_PREF_USER_ID = "userId";
    public static final String SAHALATH_PREF_USER_TYPE = "userType";

    /**********************************************************
     * ************************* URL VALUES  *******************
     ************************************************************/
    public static final String SAHALATH_BASE_URL = "http://apisahalat.mawaqaademo.com/api";
    public static final String SAHALATH_BASE_THUMB_URL = "http://apisahalat.mawaqaademo.com";
//    public static final String SAHALATH_URL_LOGIN = SAHALATH_BASE_URL + "/General/Login";

    public static String EMAIL = "";
    public static String PASSWORD = "";
    public static final String SAHALATH_URL_REGISTRATION_EN = SAHALATH_BASE_URL + "/General/Registration/1";
    public static final String SAHALATH_URL_REGISTRATION_AR = SAHALATH_BASE_URL + "/General/Registration/2";
    public static final String SAHALATH_URL_HOMEPAGE_EN = SAHALATH_BASE_URL + "/restaurant/GetCuisines/1";
    public static final String SAHALATH_URL_ADD_USER = SAHALATH_BASE_URL + "/General/AddCustomer";
    public static final String SAHALAT_URL_BASE_ORDER_URL = "http://sahalat.fintolog.com";
    //public static final String SAHALAT_URL_BASE_ORDER_URL = "http://104.236.245.232";
    public static String SAHALAT_USER_TOKEN = "";
    public static boolean DRIVER_ISONLINE = false;
   // public static CatLoadingView VIEW_CAT_LOADING = new CatLoadingView();
    public static SahalathBaseActivity MAIN_ACTIVITY = null;
    public static ArrayList<Integer> ORDER_ID = new ArrayList<>();


    public static final String loginUrl(String email, String password, String token){
        EMAIL = email;
        PASSWORD = password;
        return SAHALAT_URL_BASE_ORDER_URL + "/login?ajaxRequest=1&email=" + email + "&password=" + password + "&android_device_id=" + token;
    }

    public static final String changeUserStatus(String userId, String status, String token){
       return SAHALAT_URL_BASE_ORDER_URL + "/admin/restaurant_users/update-user-status?users_id=" + userId + "&status=" + status + "&token=" + token;
    }

    public static final String changeUserStatus(String userId, String status){
        return SAHALAT_URL_BASE_ORDER_URL + "/admin/restaurant_users/update-user-status?users_id=" + userId + "&status=" + status;
    }

    public static final String changeServiceBoyStatus(String userId, String status){
        return SAHALAT_URL_BASE_ORDER_URL + "/admin/service_boy/update-user-status?users_id=" + userId + "&status=" + status;
    }

    public static final String changeServiceBoyStatus(String userId, String status, String token){
        return SAHALAT_URL_BASE_ORDER_URL + "/admin/service_boy/update-user-status?users_id=" + userId + "&status=" + status + "&token=" + token;
    }

    public static final String updateGroupOfOrderFriver(String statusٍ){
        return SAHALAT_URL_BASE_ORDER_URL + "/admin/order_driver/update-order-driver-group?ajaxRequest=1" + "status=" + statusٍ;
    }

    public static final String loadOrders(String status, String token){
        return SAHALAT_URL_BASE_ORDER_URL + "/admin/order_driver?ajaxRequest=1&status=" + status + "&token=" + token;
    }

    public static final String  reportIssue(String id, String notes, String token){
        return SAHALAT_URL_BASE_ORDER_URL + "/admin/order_driver/issue_report?axajRequest=1&id=" + id + "&note=" + notes+ "&token=" + token;
    }

    public static final String updateOrderToAcceptedOrRejected(String status, String id){
        return SAHALAT_URL_BASE_ORDER_URL + "/admin/order_driver/update-order-driver?ajaxRequest=1&status=" + status + "&id=" + id;
    }


    public static String isLogin = "No";
    public static String USER_DEVICE_ID = "";

    public static final String SAHALATH_URL_LOGIN = loginUrl(EMAIL, PASSWORD, USER_DEVICE_ID);

    public static String locationUrl(){
        return SAHALAT_URL_BASE_ORDER_URL + "/common/users/gitlocation";
    }


    /**********************************************************
     * ************************* JSON OBJECT VALUES  *******************
     ************************************************************/
    public static final String SAHALATH_SECURITY_KEY = "WebnaSecurityKey";
    public static final String SAHALATH_JSON_TAG_LOGIN_MAIL = "Cus_Email";
    public static final String SAHALATH_JSON_TAG_LOGIN_PASSWORD = "Cus_Password";
    public static final String SAHALATH_JSON_TAG_SUCCESS = "Success";
    public static final String SAHALATH_JSON_TAG_USER_ID = "Cus_Id";
    public static final String SAHALATH_JSON_TAG_USER_TYPE = "UserType";
    public static final String SAHALATH_JSON_TAG_COMPANY_LIST = "companyList";
    public static final String SAHALATH_JSON_TAG_OCCUPATION_LIST = "occupationList";
    public static final String SAHALATH_JSON_TAG_COMPANY_ID = "companyId";
    public static final String SAHALATH_JSON_TAG_COMPANY_NAME = "companyName";
    public static final String SAHALATH_JSON_TAG_OCCUPATION_ID = "occupationId";
    public static final String SAHALATH_JSON_TAG_OCCUPATION_NAME = "occupationName";
    public static final String SAHALATH_JSON_TAG_REG_EMAIL = "customerEmail";
    public static final String SAHALATH_JSON_TAG_REG_FIRST_NAME = "FirstName";
    public static final String SAHALATH_JSON_TAG_REG_LAST_NAME = "lastName";
    public static final String SAHALATH_JSON_TAG_REG_MOBILE = "mobile";
    public static final String SAHALATH_JSON_TAG_REG_LAND_PHONE = "landPhone";
    public static final String SAHALATH_JSON_TAG_REG_GENDER = "gender";
    public static final String SAHALATH_JSON_TAG_REG_PASSWORD = "password";
    public static final String SAHALATH_JSON_TAG_REG_PACI = "PACINumber";
    public static final String SAHALATH_JSON_TAG_REG_OCCUPATION = "occupation";
    public static final String SAHALATH_JSON_TAG_REG_COMMUNICATION = "communication_company";
    public static final String SAHALATH_JSON_TAG_REG_DELIVERY_ADDRESS = "deliveryAddress";
    public static final String SAHALATH_JSON_TAG_REG_ADDRESS_TYPE = "addressType";
    public static final String SAHALATH_JSON_TAG_REG_DELIVERY_AREA = "deliveryArea";
    public static final String SAHALATH_JSON_TAG_REG_BLOCK = "block";
    public static final String SAHALATH_JSON_TAG_REG_JUDDA = "judda";
    public static final String SAHALATH_JSON_TAG_REG_PROFILE = "profileName";
    public static final String SAHALATH_JSON_TAG_REG_STREET_ADDRESS = "streetAddress";
    public static final String SAHALATH_JSON_TAG_REG_HOUSE_NUMBER = "houseNumber";
    public static final String SAHALATH_JSON_TAG_REG_LOCATION = "location";
    public static final String SAHALATH_JSON_TAG_REG_LATITUDE = "Latitude";
    public static final String SAHALATH_JSON_TAG_REG_LONGITUDE = "Logitude";
    public static final String SAHALATH_JSON_TAG_CUISINE_LIST = "Cuisines";
    public static final String SAHALATH_JSON_TAG_CUISINE_NAME = "CuisineName";
    public static final String SAHALATH_JSON_TAG_CUISINE_ID = "CuisineId";
    public static final String SAHALATH_JSON_TAG_CUISINE_IMAGE_URL = "CuisineImage";
    public static final String SAHALATH_JSON_TAG_CUISINE_AREAS = "availableAreas";
    public static final String SAHALATH_JSON_TAG_CUISINE_AREA_ID = "AreaId";
    public static final String SAHALATH_JSON_TAG_CUISINE_AREA_NAME = "AreaName";
    public static final String SAHALATH_JSON_TAG_RESULT_CODE = "resultCode";
    public static final String SAHALATH_JSON_TAG_CUSTOMER_ID="CustomerId";


    public static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    /***************************************************  Static Variable To Save User ID ****************************************
     */
    public static Integer SAHALAT_USER_ID_LOGIN = 0;
    public static Integer CLICKED_STATUS = 0;

    /****************************************************************************************
     LOCATION PREVIOUS
     */

    public static Location LOCATION_TRACK_PREVIOUS = null;
    public static Location LOCATION_TRACK_NEW = null;



}
