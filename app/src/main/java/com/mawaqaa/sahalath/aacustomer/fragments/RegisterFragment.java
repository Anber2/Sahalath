package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 3/13/2017.
 */

public class RegisterFragment extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "RegisterFragment";
    private int USERTYPE;
    private static int CUSTOMER = 0, DRIVER = 1, SERVICEBOY = 2;
    public SahalathBaseActivity Activity;
    Button submit, reset;
    String name, mail, phone, flatNo, block;
    LinearLayout llCustomer, llDriver, llServiceBoy, llSubmit;
    EditText editTextName, editTextMail, editTextPhone, editTextFlatNo, editTextBlock;
    Spinner spinnerUserType, spinnerPlace;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_register, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        submit = (Button) v.findViewById(R.id.btn_submit_register);
        reset = (Button) v.findViewById(R.id.btn_reset_register);
        editTextName = (EditText) v.findViewById(R.id.et_reg_full_name);
        editTextMail = (EditText) v.findViewById(R.id.et_reg_mail);
        editTextPhone = (EditText) v.findViewById(R.id.et_reg_telephone);
        editTextBlock = (EditText) v.findViewById(R.id.et_block);
        editTextFlatNo = (EditText) v.findViewById(R.id.et_flat_no);
        spinnerPlace = (Spinner) v.findViewById(R.id.spinner_reg_place);
        spinnerUserType = (Spinner) v.findViewById(R.id.spinner_reg_user_type);
        llCustomer = (LinearLayout) v.findViewById(R.id.ll_reg_customer);
        llSubmit = (LinearLayout) v.findViewById(R.id.ll_reg_submit);

        submit.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_register:
                if (USERTYPE == CUSTOMER) {
                    customerRegisterSubmit();
                } else if (USERTYPE == DRIVER) {
                    driverRegisterSubmit();
                } else if (USERTYPE == SERVICEBOY) {
                    serviceBoyRegisterSubmit();
                }
                break;
            case R.id.btn_reset_register:
                if (USERTYPE == CUSTOMER) {
                    resetCustomerDetails();
                } else if (USERTYPE == DRIVER) {
                    resetDriverDetails();
                } else if (USERTYPE == SERVICEBOY) {
                    resetServiceBoyDetails();
                }
                break;
            default:
                break;

        }
    }

    private void driverRegisterSubmit() {

    }

    private void resetDriverDetails() {

    }


    private void serviceBoyRegisterSubmit() {

    }
    private void resetServiceBoyDetails() {

    }

    private void customerRegisterSubmit() {
        name = editTextName.getText().toString();
        mail = editTextMail.getText().toString();
        phone = editTextPhone.getText().toString();
        block = editTextBlock.getText().toString();
        flatNo = editTextFlatNo.getText().toString();
    }

    private void resetCustomerDetails() {
        editTextName.setText("");
        editTextMail.setText("");
        editTextPhone.setText("");
        editTextBlock.setText("");
        editTextFlatNo.setText("");
    }


}