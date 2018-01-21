package com.mawaqaa.sahalath.aaserviceboy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 4/3/2017.
 */

public class ServiceBoyAssignedWorkDetailsFragment extends SahalathBaseFragment {
    private String TAG = "AssignedWorkDetai";
    Button buttonAcceptWork, buttonRejectWork;
    TextView textViewComplaintID, textViewDriverName, textViewLocationName, textViewTelePhone, textViewMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SahalathMainActivity.hideUpButton();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sb_assigned_work_details, container,
                false);
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        buttonAcceptWork = (Button) v.findViewById(R.id.btn_work_details_accept);
        buttonRejectWork = (Button) v.findViewById(R.id.btn_work_details_reject);
        textViewComplaintID = (TextView) v.findViewById(R.id.tv_assigned_details_complaint_id_value);
        textViewDriverName = (TextView) v.findViewById(R.id.tv_assigned_details_driver_name_value);
        textViewLocationName = (TextView) v.findViewById(R.id.tv_assigned_details_location_value);
        textViewTelePhone = (TextView) v.findViewById(R.id.tv_assigned_details_telephone_value);
        textViewMessage = (TextView) v.findViewById(R.id.tv_assigned_service_req_message_value);
    }
}
