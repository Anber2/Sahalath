package com.mawaqaa.sahalath.aaserviceboy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aaserviceboy.adapter.CompletedWorksAdapter;
import com.mawaqaa.sahalath.aaserviceboy.data.ServiceRequestsData;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 4/7/2017.
 */

public class ServiceBoyCompletedWorksListFragments extends SahalathBaseFragment {
    private String TAG = "SBoyCompletedWorksListFrag";
    public SahalathBaseActivity Activity;
    ListView listViewCompletedWorks;
    ArrayList<ServiceRequestsData> serviceRequestsDatas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showUpButton();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sb_completed_work_list, container,
                false);
        initView(v);
        loadPendingRequests();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadPendingRequests() {
        CompletedWorksAdapter adapter = new CompletedWorksAdapter(Activity, serviceRequestsDatas);
        listViewCompletedWorks.setAdapter(adapter);
    }

    private void initView(View v) {
        listViewCompletedWorks = (ListView) v.findViewById(R.id.list_view_completed_works);
    }

}
