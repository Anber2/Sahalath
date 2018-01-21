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
import com.mawaqaa.sahalath.aacustomer.Data.CommunicationCompany;

import java.util.ArrayList;

/**
 * Created by anson on 4/17/2017.
 */

public class CommunicationCompanyAdapter extends ArrayAdapter<CommunicationCompany> implements SpinnerAdapter {
    private final Context context;
    private ArrayList<CommunicationCompany> communicationCompanies;
    LayoutInflater inflater;
    CommunicationCompany communicationCompany = null;

    public CommunicationCompanyAdapter(Context context, int res, ArrayList<CommunicationCompany> communicationCompanies) {
        super(context, res, communicationCompanies);
        this.context = context;
        this.communicationCompanies = communicationCompanies;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return communicationCompanies.size();
    }

    @Nullable
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.single_registration_spinner_item, parent, false);
        }
        communicationCompany = communicationCompanies.get(position);
        TextView companyName = (TextView) view.findViewById(R.id.tv_company_spinner);
        companyName.setText(communicationCompany.companyName);
        return view;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.single_registration_spinner_item, parent, false);
        }
        communicationCompany = communicationCompanies.get(position);
        TextView companyName = (TextView) view.findViewById(R.id.tv_company_spinner);
        companyName.setText(communicationCompany.companyName);
        return view;
    }
}
