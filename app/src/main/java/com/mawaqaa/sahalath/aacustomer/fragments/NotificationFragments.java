package com.mawaqaa.sahalath.aacustomer.fragments;

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
import com.mawaqaa.sahalath.aacustomer.Data.NotificationData;
import com.mawaqaa.sahalath.aacustomer.adapters.NotificationAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;

import java.util.ArrayList;

/**
 * Created by anson on 3/13/2017.
 */

public class NotificationFragments extends SahalathBaseFragment {
    private String TAG = "NotificationFragments";
    public SahalathBaseActivity Activity;
    ListView listViewNotification;
    ArrayList<NotificationData> notificationDatas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_notification, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        loadNotifications();
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        listViewNotification = (ListView) v.findViewById(R.id.list_view_notifications);
    }

    private void loadNotifications() {
        NotificationAdapter adapter = new NotificationAdapter(Activity, notificationDatas);
        listViewNotification.setAdapter(adapter);
    }
}
