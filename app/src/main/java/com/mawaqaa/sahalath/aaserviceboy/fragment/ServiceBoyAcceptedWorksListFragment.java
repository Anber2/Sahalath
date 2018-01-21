package com.mawaqaa.sahalath.aaserviceboy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aaserviceboy.adapter.AcceptedWorksAdapter;
import com.mawaqaa.sahalath.aaserviceboy.data.ServiceRequestsData;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 4/3/2017.
 */

public class ServiceBoyAcceptedWorksListFragment extends SahalathBaseFragment {
    public static final String TAG = "ServiceBoyAcceptedWorksListFragment";
    ListView listViewAcceptedWorks;
    ArrayList<ServiceRequestsData> serviceRequestsDatas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SahalathMainActivity.hideUpButton();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sb_accepted_work_list, container,
                false);
        initView(v);
        loadDetails();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadDetails() {
        AcceptedWorksAdapter acceptedWorksAdapter = new AcceptedWorksAdapter(Activity, serviceRequestsDatas);
        listViewAcceptedWorks.setAdapter(acceptedWorksAdapter);
    }

    private void initView(View v) {
        listViewAcceptedWorks = (ListView) v.findViewById(R.id.list_view_sb_accepted_works);
    }
}
