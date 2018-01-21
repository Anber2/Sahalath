package com.mawaqaa.sahalath.aadriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aadriver.data.DriverOrderData;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.volley.CommandFactory;

import org.json.JSONObject;

/**
 * Created by anson on 3/31/2017.
 */

public class PendingOrderDetailsFragments extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "PendingOrderDetailsFrag";
    public SahalathBaseActivity Activity;
    Button buttonAcceptOrder, buttonCallAdmin;
    TextView textViewOrderID, textViewResturantName, textViewResturantLocation, textViewCustomerName, textViewCustomerPhone, textViewCustomerBuilding, textViewCustomerBlock, textViewCustomerPlace, textViewDeliverTime;
    DriverOrderData objectData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_driver_pending_order_details, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);

        Bundle bundle = this.getArguments();
        objectData = (DriverOrderData) bundle.getSerializable("serializObjectOrder");

        initView(v);

        if (objectData != null) {
            textViewCustomerBuilding.setText(objectData.customerBuilding);
            textViewCustomerPhone.setText(objectData.telephoneNumber);
            textViewCustomerName.setText(objectData.customerName);
            textViewResturantLocation.setText(objectData.restaurantLocation);
            textViewResturantName.setText(objectData.restaurantName);
            textViewOrderID.setText(objectData.orderId);
            textViewCustomerBlock.setText(objectData.customerBlock);
            textViewCustomerPlace.setText(objectData.customerStreet);
            textViewDeliverTime.setText(objectData.deliverTime);

        }

        AppConstants.CURRENT_FRAGMENT = this;

        AppConstants.CURRENT_FRAGMENT = this;







        return v;
    }

    private void initView(View v) {
        buttonCallAdmin = (Button) v.findViewById(R.id.btn_order_details_call_admin);
        buttonAcceptOrder = (Button) v.findViewById(R.id.btn_order_details_accept_order);

        textViewOrderID = (TextView) v.findViewById(R.id.tv_pending_order_details_id_value);
        textViewResturantName = (TextView) v.findViewById(R.id.tv_pending_order_details_rest_name_value);
        textViewResturantLocation = (TextView) v.findViewById(R.id.tv_pending_order_details_rest_location_value);
        textViewCustomerName = (TextView) v.findViewById(R.id.tv_pending_order_details_cst_name_value);
        textViewCustomerPhone = (TextView) v.findViewById(R.id.tv_pending_order_details_cst_telephone_value);
        textViewCustomerBuilding = (TextView) v.findViewById(R.id.tv_pending_order_details_cst_building_value);
        textViewCustomerBlock = (TextView) v.findViewById(R.id.tv_pending_order_details_cst_block_value);
        textViewCustomerPlace = (TextView) v.findViewById(R.id.tv_pending_order_details_cst_place_value);
        textViewDeliverTime = (TextView) v.findViewById(R.id.tv_delivery_time_value);



        buttonAcceptOrder.setOnClickListener(this);
        buttonCallAdmin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_details_call_admin:
                break;
            case R.id.btn_order_details_accept_order:

                JSONObject mJsonObject = new JSONObject();
                CommandFactory mCommandFactory = new CommandFactory();
                mCommandFactory.sendGetCommand(AppConstants.updateOrderToAcceptedOrRejected("1", objectData.orderId), "",  null, PendingOrderDetailsFragments.this);

                break;
            default:
                break;

        }
    }
}
