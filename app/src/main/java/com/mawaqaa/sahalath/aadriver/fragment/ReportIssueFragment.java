package com.mawaqaa.sahalath.aadriver.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseActivity;
import com.mawaqaa.sahalath.aaactivities.SahalathBaseFragment;
import com.mawaqaa.sahalath.aaactivities.SahalathMainActivity;
import com.mawaqaa.sahalath.aadriver.data.DriverOrderData;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.volley.CommandFactory;

import org.json.JSONObject;

/**
 * Created by anson on 3/15/2017.
 */

public class ReportIssueFragment extends SahalathBaseFragment implements View.OnClickListener {
    private String TAG = "ReportIssueFragment";
    public SahalathBaseActivity Activity;
    Button buttonCallSupport, buttonSendMessage;
    Spinner spinnerIssueTtype;
    EditText editTextMessage;
    DriverOrderData objectData;
    static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    Intent mIntent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = (SahalathBaseActivity) this.getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_driver_report_issue, container,
                false);
        SahalathMainActivity.showUpButton();
        initView(v);
        AppConstants.CURRENT_FRAGMENT = this;
        Bundle bundle = this.getArguments();
        objectData = (DriverOrderData) bundle.getSerializable("serializObjectOrder");
        return v;
    }

    private void initView(View v) {
        buttonCallSupport = (Button) v.findViewById(R.id.btn_issue_call_support);
        buttonSendMessage = (Button) v.findViewById(R.id.btn_issue_send_message);
        editTextMessage = (EditText) v.findViewById(R.id.et_issue_message);
        spinnerIssueTtype = (Spinner) v.findViewById(R.id.spinner_issue_type);

        buttonCallSupport.setOnClickListener(this);
        buttonSendMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_issue_call_support:
                String number = ("tel:0096555616416");
                mIntent = new Intent(Intent.ACTION_CALL);
                mIntent.setData(Uri.parse(number));
// Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(ReportIssueFragment.this.getActivity(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ReportIssueFragment.this.getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                    // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    //You already have permission
                    try {
                        startActivity(mIntent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_issue_send_message:
                if(editTextMessage.getText().toString().equals("")){
                    Toast.makeText(ReportIssueFragment.this.getActivity(), "You have to fill message field please.....", Toast.LENGTH_LONG).show();
                }else{
                    registeIssue();
                }
                break;
            default:
                break;
        }
    }

    private void registeIssue() {
        JSONObject mJsonObject = new JSONObject();
        CommandFactory mCommandFactory = new CommandFactory();
        mCommandFactory.sendGetCommand(AppConstants.reportIssue(objectData.orderId, editTextMessage.getText().toString(),AppConstants.SAHALAT_USER_TOKEN), "", null, ReportIssueFragment.this);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    String number = ("tel:0096555616416");
                    mIntent = new Intent(Intent.ACTION_CALL);
                    mIntent.setData(Uri.parse(number));
                    startActivity(mIntent);


                    } else {
                        //You already have permission
                        try {
                            startActivity(mIntent);
                        } catch(SecurityException e) {
                            e.printStackTrace();
                        }
                    }

                }



            // other 'case' lines to check for other
            // permissions this app might request
        }
    }





}
