package com.mawaqaa.sahalath.aaactivities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mawaqaa.applocationtrack.LocationTracker;
import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aacustomer.fragments.AllRestListPageFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.DynamicSearchFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.HomeFragment;
import com.mawaqaa.sahalath.aacustomer.fragments.MostSellingDishesFragments;
import com.mawaqaa.sahalath.aacustomer.fragments.NewRestListPageFragment;
import com.mawaqaa.sahalath.aadriver.fragment.PendingOrdersFragment;
import com.mawaqaa.sahalath.aaserviceboy.fragment.ServiceBoyAssignedWorkListFragment;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.interfaces.BottomBarHandlingUtility;
import com.mawaqaa.sahalath.interfaces.FillSpinnerDrawerLayout;
import com.mawaqaa.sahalath.listeners.DrawerClickListeners;
import com.mawaqaa.sahalath.utils.LocalHelper;
import com.mawaqaa.sahalath.utils.PreferenceUtil;
import com.mawaqaa.sahalath.volley.CommandFactory;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by anson on 3/1/2017.
 */

public class SahalathMainActivity extends SahalathBaseActivity implements View.OnClickListener, BottomBarHandlingUtility, FillSpinnerDrawerLayout {
    public static final String TAG = "SahalathMainActivity";
    public static ImageButton toolbarAccount, drawerToggleButton, toolBarLanguage;
    public static boolean isDriverOnline;
    public static boolean isGalleryViewPagerVisible = false;
    /**
     * Drawer Objects
     **/
    public static DrawerLayout drawerLayout;
    public static NavigationView mNavigationView;
    public static TextView tvCstDrawerHome, tvCstDrawerAbout, tvCstDrawerCart, tvCstDrawerAccount, tvCstDrawerOrder,
            tvCstDrawerRegistration, tvCstDrawerContactUs, tvCstDrawerNotification, tvCstDrawerOffers, tvCstDrawerLogout,
            tvDriverDrawerPendingOrder, tvDriverDrawerAcceptedOrder, tvDriverDrawerNavigation, tvDriverDrawerCancelledOrdres, tvDriverDrawerAccount,
            tvServiceBoyHome, tvServiceBoyPendingRequest, tvServiceBoyAcceptedWorks, tvServiceBoyCompletedWorks,
            tvServiceBoyCanceledWorks, tvServiceBoyMyAccount, tvServiceBoyLogout;
    public static boolean isDrawerOpen = false, isDriverStatusObtained = false;
    /**
     * Bottombar
     */
    public static LinearLayout llBottomBar, llBottomBarAllRest, llBottomBarNewRest, llBottomBarMostSellingDish, llBottomBarSearch;
    static String userId = null;
    static ImageButton toolbarUpButton;
    static Spinner drawerDriverSpinner, drawerServiceBoySpinner;
    SahalathBaseActivity ACTIVITY;
    Fragment fragment;
    LinearLayout linearLayoutContainer;
    View drawerView;
    Bundle mSaveInstanceBundle = null;
    private LocationTracker locationTracker;

    public static void hideBottomBar() {
        llBottomBar.setVisibility(View.GONE);
    }

    public static void showBottomBar() {
        llBottomBar.setVisibility(View.VISIBLE);
    }

    public static void hideUpButton() {
        toolbarUpButton.setVisibility(View.GONE);
    }

    public static void showUpButton() {
        toolbarUpButton.setVisibility(View.VISIBLE);
    }

    public static void disableAppBarButtons() {
        toolbarAccount.setClickable(false);
        drawerToggleButton.setClickable(false);
    }

    public static void enableAppBarButtons() {
        toolbarAccount.setClickable(true);
        drawerToggleButton.setClickable(true);
    }

    public static void setDriverStatus() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getSahalathBaseActivity(), R.array.driver_status, android.R.layout.simple_spinner_item);

      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(SahalathMainActivity.this, R.layout.spineritem, R.array.driver_status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drawerDriverSpinner.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACTIVITY = getSahalathBaseActivity();
        Log.e(TAG, "On create");
        mSaveInstanceBundle = savedInstanceState;
        setContentView(R.layout.activity_main);
        linearLayoutContainer = (LinearLayout) findViewById(R.id.fragment_container);
        setUserId();
        initBottomBar();
        AppConstants.MAIN_ACTIVITY = SahalathMainActivity.this;
        initViews();
        loadFragments();
        setDrawer();

        List<String> itemList = new ArrayList<>();
        itemList.add(getResources().getString(R.string.online));
        itemList.add(getResources().getString(R.string.offline));
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                itemList);
        if(drawerDriverSpinner != null) {
            drawerDriverSpinner.setAdapter(spinnerArrayAdapter);

            drawerDriverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    if (AppConstants.CLICKED_STATUS == 3) {
                        String status = "";
                        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                        ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);

                        String selCat = ((TextView) adapterView.getChildAt(0)).getText().toString();
                        if (selCat.equals(getResources().getString(R.string.online))) {
                            SahalathMainActivity.isDriverOnline = true;
                            status = "0";
                        } else {
                            SahalathMainActivity.isDriverOnline = false;
                            status = "1";
                        }

                        setUserStatus(status);

                        if (isDrawerOpen) {
                            drawerLayout.closeDrawer(Gravity.START);
                        }

                    } else {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                        ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }


        if(drawerServiceBoySpinner != null) {
            drawerServiceBoySpinner.setAdapter(spinnerArrayAdapter);
            drawerServiceBoySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        String status = "";
                        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                        ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);

                        String selCat = ((TextView) adapterView.getChildAt(0)).getText().toString();
                        if (selCat.equals(getResources().getString(R.string.online))) {
                            SahalathMainActivity.isDriverOnline = true;
                            status = "0";
                        } else {
                            SahalathMainActivity.isDriverOnline = false;
                            status = "1";
                        }

                        setServiceStatus(status);

                        if (isDrawerOpen) {
                            drawerLayout.closeDrawer(Gravity.START);
                        }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

//        locationTracker = new LocationTracker("my.action")
//                .setInterval(60000)
//                .setGps(true)
//                .setNetWork(false)
//                .currentLocation(new CurrentLocationReceiver(new CurrentLocationListener() {
//
//                    @Override
//                    public void onCurrentLocation(Location location) {
//                        Log.d("callback", ":onCurrentLocation" + location.getLongitude());
//                        float mSpeed = location.getSpeed();
//                        float mAccuraccy = location.getAccuracy();
//                        //     locationTracker.stopLocationService(getBaseContext());
//                    }
//
//                    @Override
//                    public void onPermissionDiened() {
//                        Log.d("callback", ":onPermissionDiened");
//                        locationTracker.stopLocationService(getBaseContext());
//                    }
//                })).start(getBaseContext(), SahalathMainActivity.this);


    }

    private void setUserStatus(String status) {
        JSONObject mJsonObject = new JSONObject();
        CommandFactory mCommandFactory = new CommandFactory();
        String url = AppConstants.changeUserStatus(String.valueOf(AppConstants.SAHALAT_USER_ID_LOGIN), status, AppConstants.SAHALAT_USER_TOKEN);
        mCommandFactory.sendGetCommand(url, "", SahalathMainActivity.this, null);
    }


    private void setServiceStatus(String status) {
        JSONObject mJsonObject = new JSONObject();
        CommandFactory mCommandFactory = new CommandFactory();
        String url = AppConstants.changeServiceBoyStatus(String.valueOf(AppConstants.SAHALAT_USER_ID_LOGIN), status, AppConstants.SAHALAT_USER_TOKEN);
        mCommandFactory.sendGetCommand(url, "", SahalathMainActivity.this, null);
    }


    private void setUserId() {

        if (PreferenceUtil.getIsLoggedIn(ACTIVITY)) {
            userId = PreferenceUtil.getUserId(ACTIVITY);
            Log.e(TAG, "User Id>>>>" + userId);
        }
    }

    private void initBottomBar() {
        llBottomBar = (LinearLayout) findViewById(R.id.ll_bottom_bar);

        llBottomBarAllRest = (LinearLayout) findViewById(R.id.ll_all_rest);
        llBottomBarNewRest = (LinearLayout) findViewById(R.id.ll_new_rest);
        llBottomBarMostSellingDish = (LinearLayout) findViewById(R.id.ll_most_selling_dish);
        llBottomBarSearch = (LinearLayout) findViewById(R.id.ll_search);

        llBottomBarMostSellingDish.setOnClickListener(this);
        llBottomBarSearch.setOnClickListener(this);
        llBottomBarNewRest.setOnClickListener(this);
        llBottomBarAllRest.setOnClickListener(this);
    }

    private void initViews() {
        drawerToggleButton = (ImageButton) findViewById(R.id.img_btn_appbar_toggle_drawer);
        toolbarUpButton = (ImageButton) findViewById(R.id.img_btn_appbar_up);
        toolbarAccount = (ImageButton) findViewById(R.id.img_btn_appbar_account);
        toolBarLanguage = (ImageButton) findViewById(R.id.img_btn_appbar_lang);

        drawerToggleButton.setOnClickListener(this);
        toolbarUpButton.setOnClickListener(this);
        toolbarAccount.setOnClickListener(this);
        toolBarLanguage.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        locationTracker.onRequestPermission(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void setDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) linearLayoutContainer.getLayoutParams();

        switch (BaseActivity.USER_TYPE) {
            case AppConstants.USER_CUSTOMER:
                drawerView = mNavigationView.inflateHeaderView(R.layout.drawer_customer_layout);
                setCustomerDrawerItems();
                break;
            case AppConstants.USER_DRIVER:
                drawerView = mNavigationView.inflateHeaderView(R.layout.drawer_driver_layout);
                setDriverDrawerItems();
                linearLayoutContainer.setLayoutParams(params);
                hideBottomBar();
                params.setMargins(0, 0, 0, 0);

//                locationTracker = new LocationTracker("my.action")
//                        .setInterval(60000)
//                        .setGps(true)
//                        .setNetWork(false)
//                        .currentLocation(new CurrentLocationReceiver(new CurrentLocationListener() {
//
//                            @Override
//                            public void onCurrentLocation(Location location) {
//                                Log.d("callback", ":onCurrentLocation" + location.getLongitude());
//                                float mSpeed = location.getSpeed();
//                                float mAccuraccy = location.getAccuracy();
//                                //     locationTracker.stopLocationService(getBaseContext());
//                            }
//
//                            @Override
//                            public void onPermissionDiened() {
//                                Log.d("callback", ":onPermissionDiened");
//                                locationTracker.stopLocationService(getBaseContext());
//                            }
//                        })).start(getBaseContext(), SahalathMainActivity.this);
                break;
            case AppConstants.USER_SERVICE_BOY:
                drawerView = mNavigationView.inflateHeaderView(R.layout.drawer_serviceboy_layout);
                setServiceDrawerItems();
                linearLayoutContainer.setLayoutParams(params);
                hideBottomBar();
                params.setMargins(0, 0, 0, 0);
                break;
            default:
                break;
        }
    }

    private void setDriverDrawerItems() {
        drawerDriverSpinner = (Spinner) drawerView.findViewById(R.id.spinner_drawer_driver_status);
        tvDriverDrawerPendingOrder = (TextView) drawerView.findViewById(R.id.tv_driver_drawer_pending_order);
        tvDriverDrawerAcceptedOrder = (TextView) drawerView.findViewById(R.id.tv_driver_drawer_accepted_orders);
        tvDriverDrawerNavigation = (TextView) drawerView.findViewById(R.id.tv_driver_drawer_navigation_orders);
        tvDriverDrawerCancelledOrdres = (TextView) drawerView.findViewById(R.id.tv_driver_drawer_canceled_orders);
        tvDriverDrawerAccount = (TextView) drawerView.findViewById(R.id.tv_driver_drawer_my_account);

        tvDriverDrawerPendingOrder.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvDriverDrawerPendingOrder));
        tvDriverDrawerAcceptedOrder.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvDriverDrawerAcceptedOrder));
        tvDriverDrawerCancelledOrdres.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvDriverDrawerCancelledOrdres));
        tvDriverDrawerNavigation.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvDriverDrawerNavigation));
        tvDriverDrawerAccount.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvDriverDrawerAccount));
    }

    private void setServiceDrawerItems() {
        drawerServiceBoySpinner = (Spinner)drawerView.findViewById(R.id.spinner_drawer_service_boy_status);
        tvServiceBoyHome = (TextView) drawerView.findViewById(R.id.tv_sboy_drawer_home);
//        tvServiceBoyPendingRequest = (TextView) drawerView.findViewById(R.id.tv_sboy_pending_request);
      //  tvServiceBoyAcceptedWorks = (TextView) drawerView.findViewById(R.id.tv_sboy_drawer_accepted_works);
        tvServiceBoyCompletedWorks = (TextView) drawerView.findViewById(R.id.tv_sboy_drawer_complete_works);
        tvServiceBoyCanceledWorks = (TextView) drawerView.findViewById(R.id.tv_sboy_drawer_canceled_works);
        tvServiceBoyMyAccount = (TextView) drawerView.findViewById(R.id.tv_sboy_drawer_my_account);
        tvServiceBoyLogout = (TextView) drawerView.findViewById(R.id.tv_sboy_drawer_logout);

        tvServiceBoyCompletedWorks.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvServiceBoyCompletedWorks));
        tvServiceBoyCanceledWorks.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvServiceBoyCanceledWorks));
        tvServiceBoyMyAccount.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvServiceBoyMyAccount));
        tvServiceBoyLogout.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvServiceBoyLogout));
//        tvServiceBoyPendingRequest.setOnClickListener(new DrawerClickListeners(getApplicationContext(), drawerLayout, tvServiceBoyPendingRequest));
        tvServiceBoyHome.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvServiceBoyHome));
        //tvServiceBoyAcceptedWorks.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvServiceBoyHome));
    }

    private void setCustomerDrawerItems() {
        tvCstDrawerHome = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_home);
        tvCstDrawerAbout = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_about);
        tvCstDrawerCart = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_cart);
        tvCstDrawerAccount = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_account);
        tvCstDrawerOrder = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_history);
        tvCstDrawerRegistration = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_registration);
        tvCstDrawerContactUs = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_contact_us);
        tvCstDrawerNotification = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_notification);
        tvCstDrawerOffers = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_offers);
        tvCstDrawerLogout = (TextView) drawerView.findViewById(R.id.tv_cst_drawer_logout);

        tvCstDrawerHome.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerHome));
        tvCstDrawerAbout.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerAbout));
        tvCstDrawerCart.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerCart));
        tvCstDrawerAccount.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerAccount));
        tvCstDrawerOrder.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerOrder));
        tvCstDrawerRegistration.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerRegistration));
        tvCstDrawerContactUs.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerContactUs));
        tvCstDrawerNotification.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerNotification));
        tvCstDrawerOffers.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerOrder));
        tvCstDrawerLogout.setOnClickListener(new DrawerClickListeners(SahalathMainActivity.this, getApplicationContext(), drawerLayout, tvCstDrawerLogout));

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_appbar_toggle_drawer:
                if (isDrawerOpen) {
                    Log.e(TAG, "alreadey open");
                    //drawerLayout.closeDrawer(Gravity.LEFT);
                    drawerLayout.closeDrawer(Gravity.START);
                    isDrawerOpen = false;
                    break;
                } else {
                    Log.e(TAG, "drawer is closed");
                    //drawerLayout.openDrawer(Gravity.LEFT);
                    drawerLayout.openDrawer(Gravity.START);
                    isDrawerOpen = true;
                    break;
                }

            case R.id.img_btn_appbar_account:
                break;
            case R.id.img_btn_appbar_lang:
                String storedLanguage = LocalHelper.getLanguage(SahalathMainActivity.this);
                if (storedLanguage == null || storedLanguage.equals("")) {
                    String currentLanguage = Locale.getDefault().getLanguage();
                    LocalHelper.setLocale(SahalathMainActivity.this, currentLanguage);
                } else if (storedLanguage.equals("en")) {
                    LocalHelper.setLocale(SahalathMainActivity.this, "ar");
                } else if (storedLanguage.equals("ar")) {
                    LocalHelper.setLocale(SahalathMainActivity.this, "en");
                } else {

                }
                break;
            case R.id.img_btn_appbar_up:
                break;
            case R.id.ll_all_rest:
                Fragment allRestFragment = new AllRestListPageFragment();
                SahalathBaseActivity.getSahalathBaseActivity().pushFragments(allRestFragment, true, true);

                break;
            case R.id.ll_new_rest:
                Fragment newRestFragment = new NewRestListPageFragment();
                SahalathBaseActivity.getSahalathBaseActivity().pushFragments(newRestFragment, true, true);

                break;
            case R.id.ll_most_selling_dish:
                Fragment mostSellingFragment = new MostSellingDishesFragments();
                SahalathBaseActivity.getSahalathBaseActivity().pushFragments(mostSellingFragment, true, true);

                break;
            case R.id.ll_search:
                Fragment dynamicSearchFragment = new DynamicSearchFragment();
                SahalathBaseActivity.getSahalathBaseActivity().pushFragments(dynamicSearchFragment, true, true);

                break;
            default:
                break;
        }
    }

    private void loadFragments() {
        Log.e(TAG, BaseActivity.USER_TYPE + "");

        switch (BaseActivity.USER_TYPE) {
            case AppConstants.USER_CUSTOMER:
                Log.e("User Type", "USER_CUSTOMER");
                if (mSaveInstanceBundle == null) {
                    fragment = new HomeFragment();
                } else {
                    fragment = AppConstants.CURRENT_FRAGMENT;
                }
                pushFragments(fragment, false, false);
                break;
            case AppConstants.USER_DRIVER:
                Log.e("User Type", "USER_DRIVER");
                if (mSaveInstanceBundle == null) {
                    fragment = new PendingOrdersFragment();
                } else {
                    fragment = AppConstants.CURRENT_FRAGMENT;
                }
                pushFragments(fragment, false, false);
                break;
            case AppConstants.USER_SERVICE_BOY:
                Log.e("User Type", "USER_SERVICE_BOY");
                if (mSaveInstanceBundle == null) {
                    fragment = new ServiceBoyAssignedWorkListFragment();
                } else {
                    fragment = AppConstants.CURRENT_FRAGMENT;
                }
                pushFragments(fragment, false, false);
                break;
            default:
                break;
        }
    }

    @Override
    public void allRestPage() {
        llBottomBarAllRest.setSelected(true);
        llBottomBarNewRest.setSelected(false);
        llBottomBarMostSellingDish.setSelected(false);
        llBottomBarSearch.setSelected(false);
    }

    @Override
    public void newRestPage() {
        llBottomBarAllRest.setSelected(false);
        llBottomBarNewRest.setSelected(true);
        llBottomBarMostSellingDish.setSelected(false);
        llBottomBarSearch.setSelected(false);
    }

    @Override
    public void mostSellingDishPage() {
        llBottomBarAllRest.setSelected(false);
        llBottomBarNewRest.setSelected(false);
        llBottomBarMostSellingDish.setSelected(true);
        llBottomBarSearch.setSelected(false);
    }

    @Override
    public void searchPage() {
        llBottomBarAllRest.setSelected(false);
        llBottomBarNewRest.setSelected(false);
        llBottomBarMostSellingDish.setSelected(false);
        llBottomBarSearch.setSelected(true);
    }

    @Override
    public void fillOnlineOrOfflineFirstTime() {

        if(AppConstants.CLICKED_STATUS == 1) {
            String status = "";
            drawerDriverSpinner.setSelection(1);

//                    if(((TextView) adapterView.getChildAt(0)).getText().toString().equals(R.string.offline)){
//                        ((TextView) adapterView.getChildAt(0)).setText(getResources().getString(R.string.online));
//                    }else {
//                        ((TextView) adapterView.getChildAt(0)).setText(getResources().getString(R.string.offline));
//                    }



            AppConstants.CLICKED_STATUS = 3;
//                    if (SahalathMainActivity.isDriverOnline == true) {
//                        ((TextView) adapterView.getChildAt(0)).setText(getResources().getString(R.string.online));
//                    } else {
//                        ((TextView) adapterView.getChildAt(0)).setText(getResources().getString(R.string.offline));
//                    }
//                String selCat = ((TextView) adapterView.getChildAt(i)).getText().toString();
        }else if(AppConstants.CLICKED_STATUS == 2) {
            String status = "";
            drawerDriverSpinner.setSelection(0);
//                    if(((TextView) adapterView.getChildAt(0)).getText().toString().equals(R.string.online)){
//                        ((TextView) adapterView.getChildAt(0)).setText(getResources().getString(R.string.offline));
//                    }else {
//                        ((TextView) adapterView.getChildAt(0)).setText(getResources().getString(R.string.online));
//                    }
            AppConstants.CLICKED_STATUS = 3;
//                    if (SahalathMainActivity.isDriverOnline == true) {
//                        ((TextView) adapterView.getChildAt(0)).setText(getResources().getString(R.string.online));
//                    } else {
//                        ((TextView) adapterView.getChildAt(0)).setText(getResources().getString(R.string.offline));
//                    }
//                String selCat = ((TextView) adapterView.getChildAt(i)).getText().toString();
        }

    }
}
