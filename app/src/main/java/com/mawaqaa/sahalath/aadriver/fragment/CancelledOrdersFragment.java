package com.mawaqaa.sahalath.aadriver.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aadriver.adapters.AcceptedOrderAdapter;
import com.mawaqaa.sahalath.aadriver.data.DriverOrderData;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.interfaces.LoadOrders;
import com.mawaqaa.sahalath.volley.CommandFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by anson on 3/31/2017.
 */

public class CancelledOrdersFragment extends SahalathBaseFragment implements LoadOrders {
    public SahalathBaseActivity Activity;
    ListView listViewAcceptedOrders;
    ArrayList<DriverOrderData> driverOrderDatas;
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
    TextView textViewPopupMsg;
    Dialog mDialog;
    Button buttonOkDismissDialog;
    private String TAG = "OrderDetailsFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_driver_cancelled_orders, container,
                false);
        SahalathMainActivity.showUpButton();

        mDialog = new Dialog(CancelledOrdersFragment.this.getActivity());
        mDialog.setContentView(R.layout.dialogpopup);
        mDialog.setCanceledOnTouchOutside(true);

        mProgressDialog = new ProgressDialog(CancelledOrdersFragment.this.getContext(), R.style.wait_spinner_style);
        mProgressDialog.setMessage(getResources().getString(R.string.loading));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        driverOrderDatas = new ArrayList<>();
        initView(v);
        loadAcceptedOrders();
        AppConstants.CURRENT_FRAGMENT = this;


        buttonOkDismissDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });


        listViewAcceptedOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DriverOrderData objectOrderDriver = driverOrderDatas.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("serializObjectOrder", objectOrderDriver);

                Fragment orderDetailsFragmnt = new OrderDetailsFragment();
                orderDetailsFragmnt.setArguments(bundle);

                SahalathMainActivity.getSahalathBaseActivity().pushFragments(orderDetailsFragmnt, true, true);

            }
        });


        return v;
    }

    private void initView(View v) {
        listViewAcceptedOrders = (ListView) v.findViewById(R.id.list_view_cancelled_order);
        buttonOkDismissDialog = (Button) mDialog.findViewById(R.id.btnOkDismissDialog);
        textViewPopupMsg = (TextView) mDialog.findViewById(R.id.textPopup);
        textViewPopupMsg.setText(getActivity().getResources().getString(R.string.empty_cancelled_order));
    }

    private void loadAcceptedOrders() {
//        AppConstants.VIEW_CAT_LOADING = new CatLoadingView();
//        AppConstants.VIEW_CAT_LOADING.show(getFragmentManager(), "");
        mProgressDialog.show();
        JSONObject mJsonObject = new JSONObject();
        CommandFactory mCommandFactory = new CommandFactory();
        mCommandFactory.sendGetCommand(AppConstants.loadOrders("2", AppConstants.SAHALAT_USER_TOKEN), "ORDER_LOAD", null, CancelledOrdersFragment.this);
    }

    @Override
    public void loadOrders(JSONArray arrayObject) {

        mProgressDialog.dismiss();
        int arraySize = arrayObject.length();
        if (arraySize > 0) {
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
        } else {
                mDialog.show();
        }
        AcceptedOrderAdapter adapter = new AcceptedOrderAdapter(Activity, driverOrderDatas);
        listViewAcceptedOrders.setAdapter(adapter);
    }

}
