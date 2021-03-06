package com.mawaqaa.sahalath.aadriver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by anson on 4/10/2017.
 */

public class DriverAccountDetails extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "DriverAccountDetails";
    public SahalathBaseActivity Activity;
    Button buttonEditInfo, buttonSaveInfo, buttonChangePassword;
    LinearLayout linearLayoutEdit, linearLayoutView;
    EditText editTextFirstName, editTextLastName, editTextMobileNumber, editTextEmailID, editTextCivilId,
            editTextDriverId, editTextDriverLicenceId;
    TextView textViewFirstName, textViewLastName, textViewMobileNumber, textViewEmailID, textViewCivilId,
            textViewDriverId, textViewDriverLicenceId;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_driver_account, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        return v;
    }

    private void initView(View v) {
        linearLayoutEdit = (LinearLayout) v.findViewById(R.id.ll_driver_account_edit);
        linearLayoutView = (LinearLayout) v.findViewById(R.id.ll_driver_account_view);
        buttonChangePassword = (Button) v.findViewById(R.id.btn_driver_account_change_pass);
        buttonEditInfo = (Button) v.findViewById(R.id.btn_driver_account_edit_info);
        buttonSaveInfo = (Button) v.findViewById(R.id.btn_driver_account_save);
        editTextFirstName = (EditText) v.findViewById(R.id.et_driver_account_first_name);
        editTextLastName = (EditText) v.findViewById(R.id.et_driver_account_last_name);
        editTextMobileNumber = (EditText) v.findViewById(R.id.et_driver_account_mobile);
        editTextEmailID = (EditText) v.findViewById(R.id.et_driver_account_email);
        editTextCivilId = (EditText) v.findViewById(R.id.et_driver_account_civil_id);
        textViewFirstName = (TextView) v.findViewById(R.id.tv_driver_account_first_name);
        textViewLastName = (TextView) v.findViewById(R.id.tv_driver_account_last_name);
        textViewCivilId = (TextView) v.findViewById(R.id.tv_driver_account_civil_id);
        textViewEmailID = (TextView) v.findViewById(R.id.tv_driver_account_email);
        textViewMobileNumber = (TextView) v.findViewById(R.id.tv_driver_account_mobile);
        buttonSaveInfo.setClickable(false);
        buttonEditInfo.setOnClickListener(this);
        buttonSaveInfo.setOnClickListener(this);
        buttonChangePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_driver_account_change_pass:
                break;
            case R.id.btn_driver_account_edit_info:
                linearLayoutView.setVisibility(View.GONE);
                linearLayoutEdit.setVisibility(View.VISIBLE);
                buttonSaveInfo.setClickable(true);
                buttonEditInfo.setClickable(false);
                break;
            case R.id.btn_driver_account_save:
                linearLayoutView.setVisibility(View.VISIBLE);
                linearLayoutEdit.setVisibility(View.GONE);
                buttonSaveInfo.setClickable(false);
                buttonEditInfo.setClickable(true);
                break;
            default:
                break;
        }
    }
}
