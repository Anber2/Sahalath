package com.mawaqaa.sahalath.aaserviceboy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 4/3/2017.
 */

public class AcceptedWorksDetailsFragment extends SahalathBaseFragment {
    private String TAG = "AcceptedWorksDetailsFragment";
    Button buttonViewAll;
    TextView textViewComplaintId, textViewDriverName, textViewLocation, textViewTelephone, textViewMessage;
    Spinner spinnerServiceStatus;

    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SahalathMainActivity.hideUpButton();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sb_accepted_work_details, container,
                false);
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        buttonViewAll = (Button) v.findViewById(R.id.btn_view_all);
        textViewComplaintId = (TextView) v.findViewById(R.id.tv_accepted_details_complaint_id_value);
        textViewDriverName = (TextView) v.findViewById(R.id.tv_accepted_details_driver_name_value);
        textViewLocation = (TextView) v.findViewById(R.id.tv_accepted_details_location_value);
        textViewTelephone = (TextView) v.findViewById(R.id.tv_accepted_details_telephone_value);
        spinnerServiceStatus = (Spinner) v.findViewById(R.id.spinner_service_order_status);
    }
}
