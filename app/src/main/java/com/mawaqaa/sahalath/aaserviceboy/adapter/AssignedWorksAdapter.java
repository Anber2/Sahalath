package com.mawaqaa.sahalath.aaserviceboy.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aaserviceboy.data.ServiceRequestsData;
import com.mawaqaa.sahalath.aaserviceboy.fragment.ServiceBoyAssignedWorkDetailsFragment;

import java.util.ArrayList;

/**
 * Created by anson on 3/15/2017.
 */

public class AssignedWorksAdapter extends ArrayAdapter<ServiceRequestsData> {
    Activity activity;
    ArrayList<ServiceRequestsData> serviceRequestsDatas;

    public AssignedWorksAdapter(Activity activity, ArrayList<ServiceRequestsData> serviceRequestsDatas) {
        super(activity, R.layout.single_sb_assigned, serviceRequestsDatas);
        this.activity = activity;
        this.serviceRequestsDatas = serviceRequestsDatas;
    }

    static class AssignedWorksViewHolder {
        RelativeLayout rlLayout;
        TextView textViewOrderId, textViewDriverName, textViewTelephone, textViewLocation,
                textViewCompalintType, textViewMessage;
        CheckBox checkBox;

    }

    @Override
    public int getCount() {
        return 8;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.single_sb_assigned, null);

            AssignedWorksViewHolder viewHolder = new AssignedWorksViewHolder();
            viewHolder.rlLayout = (RelativeLayout) rowView.findViewById(R.id.rl_assigned_order);
            viewHolder.textViewOrderId = (TextView) rowView.findViewById(R.id.tv_assigned_service_req_id_value);
            viewHolder.textViewDriverName = (TextView) rowView.findViewById(R.id.tv_assigned_service_req_driver_value);

            rowView.setTag(viewHolder);
        }
        AssignedWorksViewHolder holder = (AssignedWorksViewHolder) rowView.getTag();
        holder.rlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ServiceBoyAssignedWorkDetailsFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(fragment, true, true);
            }
        });
        return rowView;
    }
}
