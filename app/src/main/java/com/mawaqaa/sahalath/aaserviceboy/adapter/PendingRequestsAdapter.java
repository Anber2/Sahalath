package com.mawaqaa.sahalath.aaserviceboy.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaserviceboy.data.ServiceRequestsData;

import java.util.ArrayList;

/**
 * Created by anson on 3/15/2017.
 */

public class PendingRequestsAdapter extends ArrayAdapter<ServiceRequestsData> {
    Activity activity;
    ArrayList<ServiceRequestsData> serviceRequestsDatas;

    public PendingRequestsAdapter(Activity activity, ArrayList<ServiceRequestsData> serviceRequestsDatas) {
        super(activity, R.layout.single_service_pending_request_item, serviceRequestsDatas);
        this.activity = activity;
        this.serviceRequestsDatas = serviceRequestsDatas;
    }

    static class PendingRequestViewHolder {
        RelativeLayout relativeLayout;
        CheckBox checkBox;
        TextView textViewRequestId, textViewDriverName, textViewTelephone, textViewLocation,
                textViewType, textViewMessage;
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
            rowView = inflater.inflate(R.layout.single_service_pending_request_item, null);

            PendingRequestViewHolder viewHolder = new PendingRequestViewHolder();
            viewHolder.relativeLayout = (RelativeLayout) rowView.findViewById(R.id.rl_pending_order);
            viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.check_box_service_req_item);
            viewHolder.textViewRequestId = (TextView) rowView.findViewById(R.id.tv_service_req_id_value);
            viewHolder.textViewDriverName = (TextView) rowView.findViewById(R.id.tv_service_req_driver_value);
            viewHolder.textViewTelephone = (TextView) rowView.findViewById(R.id.tv_service_req_telephone_value);
            viewHolder.textViewLocation = (TextView) rowView.findViewById(R.id.tv_service_req_location_value);
            viewHolder.textViewType = (TextView) rowView.findViewById(R.id.tv_service_req_issue_type_value);
            viewHolder.textViewMessage = (TextView) rowView.findViewById(R.id.tv_service_req_message_value);
            rowView.setTag(viewHolder);
        }
        PendingRequestViewHolder holder = (PendingRequestViewHolder) rowView.getTag();
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rowView;
    }
}
