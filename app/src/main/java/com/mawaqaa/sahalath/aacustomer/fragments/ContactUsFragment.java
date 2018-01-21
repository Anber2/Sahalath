package com.mawaqaa.sahalath.aacustomer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.contants.AppConstants;

/**
 * Created by anson on 3/13/2017.
 */

public class ContactUsFragment extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "ContactUsFragment";
    public SahalathBaseActivity Activity;
    String name, mail, telephone, message;
    EditText editTextName, editTextMail, editTextTelephone, editTextMessage;
    Spinner spinnerLocation;
    Button buttonSubmit, buttonReset;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
        SahalathMainActivity.showBottomBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer_contact_us, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        editTextName = (EditText) v.findViewById(R.id.et_contact_name);
        editTextMail = (EditText) v.findViewById(R.id.et_contact_email);
        editTextTelephone = (EditText) v.findViewById(R.id.et_contact_telephone);
        editTextMessage = (EditText) v.findViewById(R.id.et_contact_message);
        spinnerLocation = (Spinner) v.findViewById(R.id.spinner_contact_location);
        buttonReset = (Button) v.findViewById(R.id.btn_reset_contact_us);
        buttonSubmit = (Button) v.findViewById(R.id.btn_submit_contact_us);

        buttonReset.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_contact_us:
                submitDetails();
                break;
            case R.id.btn_reset_contact_us:
                resetValues();
                break;
            default:
                break;
        }
    }

    private void submitDetails() {
        message = editTextMessage.getText().toString();
        name = editTextName.getText().toString();
        telephone = editTextTelephone.getText().toString();
        mail = editTextMail.getText().toString();
    }

    private void resetValues() {
        editTextName.setText("");
        editTextMail.setText("");
        editTextMessage.setText("");
        editTextTelephone.setText("");
    }
}
