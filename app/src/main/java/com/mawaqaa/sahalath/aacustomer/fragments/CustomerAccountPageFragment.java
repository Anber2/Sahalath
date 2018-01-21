package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 3/10/2017.
 */

public class CustomerAccountPageFragment extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "CustomerAccountPageFragment";
    public SahalathBaseActivity Activity;
    LinearLayout llEditText, llTextView;
    TextView textViewName, textViewMobileNo, textViewMail, textViewAddress;
    EditText editTextName, editTextMobile, editTextMail, editTextAddress;
    Button buttonEdit, buttonSave, buttonChangePassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_account, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        llEditText = (LinearLayout) v.findViewById(R.id.ll_edit_text);
        llTextView = (LinearLayout) v.findViewById(R.id.ll_textview);
        textViewName = (TextView) v.findViewById(R.id.tv_cst_name);
        textViewMobileNo = (TextView) v.findViewById(R.id.tv_cst_mobile);
        textViewMail = (TextView) v.findViewById(R.id.tv_cst_mail);
        textViewAddress = (TextView) v.findViewById(R.id.tv_cst_address);
        editTextName = (EditText) v.findViewById(R.id.et_cst_name);
        editTextMobile = (EditText) v.findViewById(R.id.et_cst_mobile);
        editTextMail = (EditText) v.findViewById(R.id.et_text_mail);
        editTextAddress = (EditText) v.findViewById(R.id.et_address);
        buttonEdit = (Button) v.findViewById(R.id.btn_edit_info);
        buttonSave = (Button) v.findViewById(R.id.btn_save_info);
        buttonChangePassword = (Button) v.findViewById(R.id.btn_change_password);

        buttonChangePassword.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_info:
                saveAccountInfo();
                break;
            case R.id.btn_edit_info:
                setValuesToEditText();
                llTextView.setVisibility(View.GONE);
                llEditText.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_change_password:
                Fragment fragment = new ChangePasswordFragment();
                SahalathMainActivity.getSahalathBaseActivity().pushFragments(fragment, true, true);
                break;
            default:
                break;
        }
    }

    private void saveAccountInfo() {

    }

    private void setValuesToEditText() {
        editTextName.setText(textViewName.getText());
        editTextMobile.setText(textViewMobileNo.getText());
        editTextMail.setText(textViewMail.getText());
        editTextAddress.setText(editTextAddress.getText());
    }
}
