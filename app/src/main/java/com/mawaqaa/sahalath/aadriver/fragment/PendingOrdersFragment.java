package com.mawaqaa.sahalath.aadriver.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aadriver.adapters.PendingOrderAdapter;
import com.mawaqaa.sahalath.aadriver.data.DriverOrderData;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.interfaces.FillSpinnerDrawerLayout;
import com.mawaqaa.sahalath.interfaces.LoadOrders;
import com.mawaqaa.sahalath.volley.CommandFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by anson on 3/15/2017.
 */

public class PendingOrdersFragment extends SahalathBaseFragment implements View.OnClickListener, LoadOrders {
    private String TAG = "PendingOrdersFragment";
    public SahalathBaseActivity Activity;
    ListView listViewPendingOrders;
    LinearLayout llStatusLayout, llButton;
    TextView textViewDriverName, textViewPopupMsg;
    Button buttonOnline, buttonOffline, buttonAcceptOrder, buttonRejectOrder, buttonOkDismissDialog;
    ArrayList<DriverOrderData> driverOrderDatas;

    Dialog mDialog;


    String resturantName = "";
    String resturantAddress = "";
    String userName = "";
    String userMobile = "";
    String userAddress = "";
    String building = "";
    String block = "";
    String street = "";
    String deliveryTime = "";
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_driver_pending_orders, container,
                false);
        mDialog = new Dialog(PendingOrdersFragment.this.getActivity());
        mDialog.setContentView(R.layout.dialogpopup);
        mDialog.setCanceledOnTouchOutside(true);
        initView(v);

        mProgressDialog = new ProgressDialog(PendingOrdersFragment.this.getContext(), R.style.wait_spinner_style);
        mProgressDialog.setMessage("Loading.....");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        driverOrderDatas = new ArrayList<>();
        SahalathMainActivity.hideUpButton();
     //   SahalathMainActivity.disableAppBarButtons();
        loadPendingOrders();
        AppConstants.CURRENT_FRAGMENT = this;


        listViewPendingOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DriverOrderData objectOrderDriver = driverOrderDatas.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("serializObjectOrder", objectOrderDriver);

                Fragment pendingOrderDetailsFragmnt = new PendingOrderDetailsFragments();
                pendingOrderDetailsFragmnt.setArguments(bundle);

                SahalathMainActivity.getSahalathBaseActivity().pushFragments(pendingOrderDetailsFragmnt, true, true);

            }
        });



        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        AppConstants.ORDER_ID.clear();
    }

    private void initView(View v) {

        buttonOkDismissDialog = (Button)mDialog.findViewById(R.id.btnOkDismissDialog);
        buttonAcceptOrder = (Button) v.findViewById(R.id.btn_accept_order);
        buttonRejectOrder = (Button) v.findViewById(R.id.btn_reject_order);
        listViewPendingOrders = (ListView) v.findViewById(R.id.list_view_pending_order);
        llStatusLayout = (LinearLayout) v.findViewById(R.id.ll_driver_status);
        buttonOnline = (Button) v.findViewById(R.id.btn_driver_home_online);
        buttonOffline = (Button) v.findViewById(R.id.btn_driver_home_offline);
        textViewDriverName = (TextView) v.findViewById(R.id.tv_home_driver_name);
        textViewPopupMsg = (TextView) mDialog.findViewById(R.id.textPopup);
        llButton = (LinearLayout) v.findViewById(R.id.ll_buttons);
        Log.e(TAG,"isDriverStatusObtained"+SahalathMainActivity.isDriverStatusObtained);
        if (SahalathMainActivity.isDriverStatusObtained) {
            llStatusLayout.setVisibility(View.GONE);
        }
        textViewPopupMsg.setText(getActivity().getResources().getString(R.string.empty_pending_order));
        buttonOkDismissDialog.setOnClickListener(this);
        buttonOnline.setOnClickListener(this);
        buttonOffline.setOnClickListener(this);
        buttonRejectOrder.setOnClickListener(this);
        buttonAcceptOrder.setOnClickListener(this);
    }

    private void loadPendingOrders() {
//        AppConstants.VIEW_CAT_LOADING = new CatLoadingView();
//        AppConstants.VIEW_CAT_LOADING.show(getFragmentManager(), "");
        mProgressDialog.show();
        JSONObject mJsonObject = new JSONObject();
        CommandFactory mCommandFactory = new CommandFactory();
        mCommandFactory.sendGetCommand(AppConstants.loadOrders("2", AppConstants.SAHALAT_USER_TOKEN), "ORDER_LOAD", null, PendingOrdersFragment.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home_reject:
                Log.e(TAG, "btn_home_reject");
                break;
            case R.id.btn_home_accept:
                Log.e(TAG, "btn_home_accept");
                break;
            case R.id.btn_driver_home_offline:
                settingStatus(v);
                break;
            case R.id.btnOkDismissDialog:
                mDialog.dismiss();
                break;
            case R.id.btn_driver_home_online:
                settingStatus(v);
                break;
            default:
                break;
        }
    }


    void makeAcceptOrRejectOrder(String status){

    }


    public void settingStatus(View v) {
        SahalathMainActivity.enableAppBarButtons();
        SahalathMainActivity.isDriverStatusObtained = true;
        llStatusLayout.setVisibility(View.GONE);
        FillSpinnerDrawerLayout interfaceObject;
        switch (v.getId()) {
            case R.id.btn_driver_home_offline:
                SahalathMainActivity.isDriverOnline = false;
                AppConstants.CLICKED_STATUS = 1;
                 interfaceObject = (FillSpinnerDrawerLayout)AppConstants.MAIN_ACTIVITY;
                interfaceObject.fillOnlineOrOfflineFirstTime();
                setUserStatus("1");
                break;
            case R.id.btn_driver_home_online:
                SahalathMainActivity.isDriverOnline = true;
                AppConstants.CLICKED_STATUS = 2;
                 interfaceObject = (FillSpinnerDrawerLayout)AppConstants.MAIN_ACTIVITY;
                interfaceObject.fillOnlineOrOfflineFirstTime();
                setUserStatus("0");
                break;
            default:
                break;
        }

    }

    private void setUserStatus(String status) {
        JSONObject mJsonObject = new JSONObject();
        CommandFactory mCommandFactory = new CommandFactory();
        String urll = AppConstants.changeUserStatus(String.valueOf(AppConstants.SAHALAT_USER_ID_LOGIN), status, AppConstants.SAHALAT_USER_TOKEN);
        //mCommandFactory.sendPostCommand(urll, mJsonObject, null, PendingOrdersFragment.this);
      //  new UpdateUserStatusManager(null, PendingOrdersFragment.this).updateUserStatus(String.valueOf(AppConstants.SAHALAT_USER_ID_LOGIN), status);
        mCommandFactory.sendGetCommand(urll, "", null, PendingOrdersFragment.this);
    }

    @Override
    public void loadOrders(JSONArray arrayObject) {

        mProgressDialog.dismiss();
        int arraySize = arrayObject.length();

        if(arraySize > 0) {
            for (int i = 0; i < arraySize; i++) {
                try {
                    JSONObject mObjectJson = arrayObject.getJSONObject(i);
                    JSONObject orderJsonObject = mObjectJson.getJSONObject("order");
                    JSONObject resturantJsonObject = orderJsonObject.getJSONObject("restaurant");
                    JSONObject addressJsonObject = orderJsonObject.getJSONObject("address");

                    String currentLanguage = Locale.getDefault().getLanguage();
                    int orderID = mObjectJson.getInt("id");


                    if (currentLanguage.equals("en")) {
                        resturantName = resturantJsonObject.getString("name_en");
                        resturantAddress = resturantJsonObject.getString("address_en");
                    } else {
                        resturantName = resturantJsonObject.getString("name_ar");
                        resturantAddress = resturantJsonObject.getString("address_ar");
                    }

                    userName = addressJsonObject.getString("first_name") + " " + addressJsonObject.getString("last_name");
                    userMobile = addressJsonObject.getString("mobile");
                    userAddress = addressJsonObject.getString("name");
                    block = addressJsonObject.getString("block");
                    building = addressJsonObject.getString("building");
                    street = addressJsonObject.getString("street");
                    deliveryTime = orderJsonObject.getString("deliver_time");
                    DriverOrderData objectDriverOrderData = new DriverOrderData(String.valueOf(orderID), resturantName, resturantAddress, userName, userMobile, userAddress, building, block, street, deliveryTime);
                    driverOrderDatas.add(objectDriverOrderData);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception ex) {
                    String error = ex.getMessage();
                }
            }
        }else{
            mDialog.show();
        }
        PendingOrderAdapter adapter = new PendingOrderAdapter(Activity, driverOrderDatas);
        listViewPendingOrders.setAdapter(adapter);
    }
}
