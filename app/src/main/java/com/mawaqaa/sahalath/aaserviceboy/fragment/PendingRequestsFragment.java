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
import com.mawaqaa.sahalath.aaserviceboy.adapter.PendingRequestsAdapter;
import com.mawaqaa.sahalath.aaserviceboy.data.ServiceRequestsData;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 3/15/2017.
 */

public class PendingRequestsFragment extends SahalathBaseFragment {
    public static final String TAG = "PendingRequestsFragment";
    public SahalathBaseActivity Activity;
    ListView listViewPendingRequests;
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
        View v = inflater.inflate(R.layout.fragment_sb_pending_req, container,
                false);
        initView(v);
        loadPendingRequests();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void loadPendingRequests() {
        PendingRequestsAdapter adapter = new PendingRequestsAdapter(Activity, serviceRequestsDatas);
        listViewPendingRequests.setAdapter(adapter);
    }

    private void initView(View v) {
        listViewPendingRequests = (ListView) v.findViewById(R.id.list_view_pending_requests);
    }
}
