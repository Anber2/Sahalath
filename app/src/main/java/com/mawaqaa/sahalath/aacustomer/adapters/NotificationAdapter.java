package com.mawaqaa.sahalath.aacustomer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aacustomer.Data.NotificationData;

import java.util.ArrayList;

/**
 * Created by anson on 3/13/2017.
 */

public class NotificationAdapter extends ArrayAdapter<NotificationData> {
    private String TAG = "NotificationAdapter";
    private Activity activity;
    ArrayList<NotificationData> notificationDatas;

    public NotificationAdapter(Activity activity, ArrayList<NotificationData> notificationDatas) {
        super(activity, R.layout.single_customer_notification_item, notificationDatas);
        this.activity = activity;
        this.notificationDatas = notificationDatas;
    }

    static class NotificationViewHolder {
        TextView notificationContent, notificatoinDate, notificationTime;
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
            rowView = inflater.inflate(R.layout.single_customer_notification_item, null);

            NotificationViewHolder viewHolder = new NotificationViewHolder();

            rowView.setTag(viewHolder);
        }
        NotificationViewHolder holder = (NotificationViewHolder) rowView.getTag();
        return rowView;
    }
}
