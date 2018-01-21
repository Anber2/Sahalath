package com.mawaqaa.sahalath.aacustomer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aacustomer.Data.OccupationData;

import java.util.ArrayList;

/**
 * Created by anson on 4/18/2017.
 */

public class OccupationAdapter extends ArrayAdapter<OccupationData> implements SpinnerAdapter {
    private final Context context;
    private ArrayList<OccupationData> occupationDatas;
    LayoutInflater inflater;
    OccupationData occupationData;

    public OccupationAdapter(Context context, int res, ArrayList<OccupationData> occupationDatas) {
        super(context, res, occupationDatas);
        this.context = context;
        this.occupationDatas = occupationDatas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return occupationDatas.size();
    }

    @Nullable
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.single_registration_spinner_item, parent, false);
        }
        occupationData = occupationDatas.get(position);
        TextView occupationName = (TextView) view.findViewById(R.id.tv_company_spinner);
        occupationName.setText(occupationData.occupationName);
        return view;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.single_registration_spinner_item, parent, false);
        }
        occupationData = occupationDatas.get(position);
        TextView occupationName = (TextView) view.findViewById(R.id.tv_company_spinner);
        occupationName.setText(occupationData.occupationName);
        return view;
    }
}
