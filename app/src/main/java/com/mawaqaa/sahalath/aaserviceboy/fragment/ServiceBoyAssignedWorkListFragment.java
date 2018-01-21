package com.mawaqaa.sahalath.aaserviceboy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aaserviceboy.adapter.AssignedWorksAdapter;
import com.mawaqaa.sahalath.aaserviceboy.data.ServiceRequestsData;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 3/1/2017.
 */

public class ServiceBoyAssignedWorkListFragment extends SahalathBaseFragment {
    public static final String TAG = "ServiceBoyAssignedWorkListFragment";
    ListView listViewAssignedWork;
    Button buttonAccept, buttonReject;
    LinearLayout linearLayoutButton;
    ArrayList<ServiceRequestsData> serviceRequestsDatas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SahalathMainActivity.hideUpButton();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sb_assigned_work_list, container,
                false);
        initView(v);
        loadDetails();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadDetails() {
        AssignedWorksAdapter assignedWorksAdapter = new AssignedWorksAdapter(Activity, serviceRequestsDatas);
        listViewAssignedWork.setAdapter(assignedWorksAdapter);
    }

    private void initView(View v) {
        buttonAccept = (Button) v.findViewById(R.id.btn_accept_work);
        buttonReject = (Button) v.findViewById(R.id.btn_reject_work);
        listViewAssignedWork = (ListView) v.findViewById(R.id.list_view_sb_assigned_works);
        linearLayoutButton = (LinearLayout) v.findViewById(R.id.ll_assigned_button);
    }
}
