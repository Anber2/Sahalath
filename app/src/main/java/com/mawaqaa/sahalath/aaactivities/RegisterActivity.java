package com.mawaqaa.sahalath.aaactivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mawaqaa.sahalath.R;
import com.mawaqaa.sahalath.aacustomer.Data.CommunicationCompany;
import com.mawaqaa.sahalath.aacustomer.Data.OccupationData;
import com.mawaqaa.sahalath.aacustomer.adapters.CommunicationCompanyAdapter;
import com.mawaqaa.sahalath.aacustomer.adapters.OccupationAdapter;
import com.mawaqaa.sahalath.contants.AppConstants;
import com.mawaqaa.sahalath.utils.PreferenceUtil;
import com.mawaqaa.sahalath.volley.CommandFactory;
import com.mawaqaa.sahalath.volley.VolleyUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by anson on 4/13/2017.
 */

public class RegisterActivity extends SahalathBaseActivity implements View.OnClickListener {
    private String TAG = "RegisterActivity";
    public SahalathBaseActivity Activity;
    ImageButton toolbarAccount, drawerToggleButton, toolBarLanguage, toolbarUpButton;
    EditText editTextFirstName, editTextLastName, editTextEmailId, editTextPassword, editTextPasswordConfrm, editTextMobileNumber,
            editTextHousePhoneNumber, editTextPACINumber, editTextAddressName, editTextAddressBlock, editTextAddressStreet,
            editTextAddressAvenue, editTextAddressBuilding, editTextAddressFloor, editTextAddressApartment,
            editTextAddressDirections;
    Spinner spinnerCommunication, spinnerGender, spinnerOccupation;
    RadioButton radioButtonHome, radioButtonApartment, radioButtonOffice;
    RadioGroup radioGroupAddressType;
    Button buttonSubmit, buttonReset;
    String firstName, lastName, emailId, password, confirmPassword, mobileNo, homePhoneNumber, PACINumber,
            addressName, addressBlock, addressStreet, addressAvenue, addressBuilding, addressFloor, addressApartment,
            addressDirection, genderPosition, communicationPosition, occupationPosition;
    int addressType;
    CommunicationCompanyAdapter companyAdapter;
    OccupationAdapter occupationAdapter;
    ArrayList<CommunicationCompany> communicationCompanyArrayList;
    ArrayList<OccupationData> occupationDataArrayList;
    String isSpinnerSuccess = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Activity = getSahalathBaseActivity();
        initView();
        loadSpinnerDatas();
    }

    private void initView() {
        drawerToggleButton = (ImageButton) findViewById(R.id.img_btn_appbar_toggle_drawer);
        toolbarUpButton = (ImageButton) findViewById(R.id.img_btn_appbar_up);
        toolbarAccount = (ImageButton) findViewById(R.id.img_btn_appbar_account);
        toolBarLanguage = (ImageButton) findViewById(R.id.img_btn_appbar_lang);
        editTextFirstName = (EditText) findViewById(R.id.et_reg_first_name);
        editTextLastName = (EditText) findViewById(R.id.et_reg_last_name);
        editTextEmailId = (EditText) findViewById(R.id.et_reg_mail);
        editTextPassword = (EditText) findViewById(R.id.et_reg_password);
        editTextPasswordConfrm = (EditText) findViewById(R.id.et_reg_confirm_password);
        editTextMobileNumber = (EditText) findViewById(R.id.et_reg_telephone);
        editTextHousePhoneNumber = (EditText) findViewById(R.id.et_reg_home_phone_number);
        editTextPACINumber = (EditText) findViewById(R.id.et_reg_paci_number);
        editTextAddressName = (EditText) findViewById(R.id.et_reg_flat_name);
        editTextAddressBlock = (EditText) findViewById(R.id.et_reg_block);
        editTextAddressStreet = (EditText) findViewById(R.id.et_reg_street);
        editTextAddressAvenue = (EditText) findViewById(R.id.et_reg_avenue);
        editTextAddressBuilding = (EditText) findViewById(R.id.et_reg_building);
        editTextAddressFloor = (EditText) findViewById(R.id.et_reg_floor);
        editTextAddressApartment = (EditText) findViewById(R.id.et_reg_apart_no);
        editTextAddressDirections = (EditText) findViewById(R.id.et_reg_directions);
        spinnerCommunication = (Spinner) findViewById(R.id.spinner_reg_communication);
        spinnerGender = (Spinner) findViewById(R.id.spinner_reg_gender);
        spinnerOccupation = (Spinner) findViewById(R.id.spinner_reg_occupation);
        buttonSubmit = (Button) findViewById(R.id.btn_submit_register_act);
        buttonReset = (Button) findViewById(R.id.btn_reset_register_act);
        radioGroupAddressType = (RadioGroup) findViewById(R.id.radio_group_address_type);
        radioButtonApartment = (RadioButton) findViewById((R.id.radio_btn_apartment));
        radioButtonHome = (RadioButton) findViewById(R.id.radio_btn_house);
        radioButtonOffice = (RadioButton) findViewById(R.id.radio_btn_office);

        drawerToggleButton.setVisibility(View.GONE);
        toolbarAccount.setVisibility(View.GONE);
        toolbarUpButton.setVisibility(View.GONE);

        buttonReset.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

        radioGroupAddressType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_btn_apartment:
                        addressType = 2;
                        break;
                    case R.id.radio_btn_house:
                        addressType = 1;
                        break;
                    case R.id.radio_btn_office:
                        addressType = 3;
                        break;
                    default:
                        addressType = 2;
                        break;
                }
            }
        });

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, R.layout.single_registration_spinner_item, getResources().getStringArray(R.array.gender_array));
        genderAdapter.setDropDownViewResource(R.layout.single_registration_spinner_item);
        spinnerGender.setAdapter(genderAdapter);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderPosition = Integer.toString(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void loadSpinnerDatas() {
        try {
            if (Activity.isNetworkAvailable()) {
                createJSONData();
            } else {
                Toast.makeText(Activity, getString(R.string.alert_no_internet), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {

        }
    }

    private void createJSONData() {
        try {
            startThemeSpinningWheel();
            if (VolleyUtils.volleyEnabled) {
                CommandFactory commandFactory = new CommandFactory();
                commandFactory.sendGetCommand(RegisterActivity.this, null, AppConstants.SAHALATH_URL_REGISTRATION_EN);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onRegistrationSpinnersSuccess(JSONObject jsonObject) {
        super.onRegistrationSpinnersSuccess(jsonObject);
        Log.e(TAG, "Response>>" + jsonObject);
        parseJson(jsonObject);
    }

    public void parseJson(JSONObject jsonObject) {
        try {
            if (jsonObject != null) {
                isSpinnerSuccess = jsonObject.getString(AppConstants.SAHALATH_JSON_TAG_SUCCESS);
                if (isSpinnerSuccess.equals("null")) {

                    JSONArray companyJSONArray = jsonObject.getJSONArray(AppConstants.SAHALATH_JSON_TAG_COMPANY_LIST);
                    JSONArray occupationJsonArray = jsonObject.getJSONArray(AppConstants.SAHALATH_JSON_TAG_OCCUPATION_LIST);
                    communicationCompanyArrayList = new ArrayList<>();
                    communicationCompanyArrayList.add(new CommunicationCompany(null, getResources().getString(R.string.select_communication)));
                    occupationDataArrayList = new ArrayList<>();
                    occupationDataArrayList.add(new OccupationData(null, getResources().getString(R.string.select_occupation)));
                    for (int i = 0; i < companyJSONArray.length(); i++) {

                        JSONObject companyJsonObject = companyJSONArray.getJSONObject(i);
                        communicationCompanyArrayList.add(new CommunicationCompany(
                                companyJsonObject.getString(AppConstants.SAHALATH_JSON_TAG_COMPANY_ID),
                                companyJsonObject.getString(AppConstants.SAHALATH_JSON_TAG_COMPANY_NAME)));
                    }

                    for (int i = 0; i < occupationJsonArray.length(); i++) {
                        JSONObject occupationJsonObject = occupationJsonArray.getJSONObject(i);
                        occupationDataArrayList.add(new OccupationData(
                                occupationJsonObject.getString(AppConstants.SAHALATH_JSON_TAG_OCCUPATION_ID),
                                occupationJsonObject.getString(AppConstants.SAHALATH_JSON_TAG_OCCUPATION_NAME)));
                    }
                    companyAdapter = new CommunicationCompanyAdapter(this, R.layout.single_registration_spinner_item, communicationCompanyArrayList);
                    spinnerCommunication.setAdapter(companyAdapter);
                    occupationAdapter = new OccupationAdapter(this, R.layout.single_registration_spinner_item, occupationDataArrayList);
                    spinnerOccupation.setAdapter(occupationAdapter);
                    stopThemeSpinWheel();

                    spinnerCommunication.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            communicationPosition = communicationCompanyArrayList.get(position).companyId;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    spinnerOccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            occupationPosition = occupationDataArrayList.get(position).occupationId;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    stopThemeSpinWheel();
                    Toast.makeText(this, getResources().getString(R.string.this_feature_not_available), Toast.LENGTH_LONG).show();
                }
            } else {
                stopThemeSpinWheel();
                Toast.makeText(this, getResources().getString(R.string.this_feature_not_available), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void onRegistrationSpinnersFailed(JSONObject jsonObject) {
        super.onRegistrationSpinnersFailed(jsonObject);
        stopThemeSpinWheel();
        Toast.makeText(this, getResources().getString(R.string.server_error_connection), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_register_act:
                if (registerAuthentication()) {

                    if (Integer.parseInt(genderPosition) == 1) {
                        genderPosition = "M";
                    } else {
                        genderPosition = "F";
                    }
                    submitRegistration();
                }
                break;
            case R.id.btn_reset_register_act:
                reset();
                break;
            default:
                break;
        }
    }

    private void submitRegistration() {
        startThemeSpinningWheel();
        try {
            JSONObject registrationJsonObject = new JSONObject();
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_EMAIL, emailId);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_FIRST_NAME, firstName);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_LAST_NAME, lastName);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_MOBILE, mobileNo);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_LAND_PHONE, homePhoneNumber);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_GENDER, genderPosition);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_PASSWORD, password);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_PACI, PACINumber);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_OCCUPATION, occupationPosition);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_COMMUNICATION, communicationPosition);
            registrationJsonObject.putOpt(AppConstants.SAHALATH_SECURITY_KEY, "W3BN@53CUR6ITY8");

            JSONObject addressJsonObject = new JSONObject();
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_ADDRESS_TYPE, addressType);
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_DELIVERY_AREA, "");
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_BLOCK, addressBlock);
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_JUDDA, addressAvenue);
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_PROFILE, "");
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_STREET_ADDRESS, addressStreet);
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_HOUSE_NUMBER, addressApartment);
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_LOCATION, "");
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_MOBILE, "");
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_LATITUDE, addressDirection);
            addressJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_LONGITUDE, "");

            registrationJsonObject.putOpt(AppConstants.SAHALATH_JSON_TAG_REG_DELIVERY_ADDRESS, addressJsonObject);
            Log.e(TAG, "Register json" + registrationJsonObject);
            if (VolleyUtils.volleyEnabled) {
                CommandFactory commandFactory = new CommandFactory();
                commandFactory.sendPostCommand("Registration", AppConstants.SAHALATH_URL_ADD_USER, registrationJsonObject, RegisterActivity.this, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAddUserSuccess(JSONObject jsonObject) {
        super.onAddUserSuccess(jsonObject);
        try {
            if (jsonObject != null) {
                if (jsonObject.getInt(AppConstants.SAHALATH_JSON_TAG_RESULT_CODE) == AppConstants.RESULT_CODE_OK) {
                    PreferenceUtil.setIsLoggedIn(Activity, true);
                    PreferenceUtil.setUserId(Activity, jsonObject.getString(AppConstants.SAHALATH_JSON_TAG_CUSTOMER_ID));
                    Log.e(TAG, "User Id" + jsonObject.getString(AppConstants.SAHALATH_JSON_TAG_CUSTOMER_ID));
                    PreferenceUtil.setUserType(Activity, AppConstants.USER_CUSTOMER);

                    Activity.USER_TYPE = AppConstants.USER_CUSTOMER;
                    stopThemeSpinWheel();
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_success), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent();
                    intent.setClass(Activity, SahalathMainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    stopThemeSpinWheel();
                    Toast.makeText(this, getResources().getString(R.string.login_failed), Toast.LENGTH_LONG).show();
                    reset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAddUserFailure(JSONObject jsonObject) {
        super.onAddUserFailure(jsonObject);
        stopThemeSpinWheel();
        Toast.makeText(this, getResources().getString(R.string.server_error_connection), Toast.LENGTH_LONG).show();
    }

    private boolean registerAuthentication() {
        firstName = editTextFirstName.getText().toString();
        lastName = editTextLastName.getText().toString();
        emailId = editTextEmailId.getText().toString();
        password = editTextPassword.getText().toString();
        confirmPassword = editTextPasswordConfrm.getText().toString();
        mobileNo = editTextMobileNumber.getText().toString();
        homePhoneNumber = editTextHousePhoneNumber.getText().toString();
        PACINumber = editTextPACINumber.getText().toString();
        addressName = editTextAddressName.getText().toString();
        addressBlock = editTextAddressBlock.getText().toString();
        addressStreet = editTextAddressStreet.getText().toString();
        addressAvenue = editTextAddressAvenue.getText().toString();
        addressBuilding = editTextAddressBuilding.getText().toString();
        addressFloor = editTextAddressFloor.getText().toString();
        addressApartment = editTextAddressApartment.getText().toString();
        addressDirection = editTextAddressDirections.getText().toString();
        Log.e(TAG, "OnSubmit gender>>>" + genderPosition + "comapny>>>" + communicationPosition + "occupation>>>>" + occupationPosition);
        if (firstName.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.first_name_empty), Toast.LENGTH_LONG).show();
            editTextFirstName.requestFocus();
            return false;
        }
        if (lastName.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.last_name_empty), Toast.LENGTH_LONG).show();
            editTextLastName.requestFocus();
            return false;
        }
        if (emailId.length() <= 0) {
            Toast.makeText(this, getResources().getString(R.string.email_empty), Toast.LENGTH_LONG).show();
            editTextEmailId.requestFocus();
            return false;
        } else {
            if (!emailId.matches(AppConstants.EMAIL_PATTERN)) {
                Toast.makeText(this, getResources().getString(R.string.alert_invalid_email), Toast.LENGTH_LONG).show();
                editTextEmailId.requestFocus();
                return false;
            }
        } if (emailId.length() <= 0) {
            Toast.makeText(this, getResources().getString(R.string.email_empty), Toast.LENGTH_LONG).show();
            editTextEmailId.requestFocus();
            return false;
        } else {
            if (!emailId.matches(AppConstants.EMAIL_PATTERN)) {
                Toast.makeText(this, getResources().getString(R.string.alert_invalid_email), Toast.LENGTH_LONG).show();
                editTextEmailId.requestFocus();
                return false;
            }
        }
        if (password.equals("") || confirmPassword.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.password_field_empty), Toast.LENGTH_LONG).show();
            editTextPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, getResources().getString(R.string.password_miss_match), Toast.LENGTH_LONG).show();
            editTextPassword.requestFocus();
            return false;
        }
        if (communicationPosition == null) {
            Toast.makeText(this, getResources().getString(R.string.password_miss_match), Toast.LENGTH_LONG).show();
            spinnerCommunication.requestFocus();
            return false;
        }
        if (mobileNo.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.mobile_no_empty), Toast.LENGTH_LONG).show();
            editTextMobileNumber.requestFocus();
            return false;
        }
        if (homePhoneNumber.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.home_no_empty), Toast.LENGTH_LONG).show();
            editTextHousePhoneNumber.requestFocus();
            return false;
        }
        Log.e(TAG, "Gender Position >>>>" + genderPosition);
       /* if (Integer.parseInt(genderPosition) == 0) {
            Toast.makeText(this, getResources().getString(R.string.home_no_empty), Toast.LENGTH_LONG).show();
            spinnerGender.requestFocus();
            return false;
        }*/
        if (occupationPosition == null) {
            Toast.makeText(this, getResources().getString(R.string.home_no_empty), Toast.LENGTH_LONG).show();
            spinnerOccupation.requestFocus();
            return false;
        }
        return true;
    }


    private void validateData() {

    }

    private void reset() {
        editTextFirstName.setHint(getResources().getString(R.string.first_name));
        editTextLastName.setHint(getResources().getString(R.string.last_name));
        editTextEmailId.setHint(getResources().getString(R.string.emailId));
        editTextPassword.setHint(getResources().getString(R.string.password));
        editTextPasswordConfrm.setHint(getResources().getString(R.string.confirm_password));
        editTextMobileNumber.setHint(getResources().getString(R.string.mobile_no));
        editTextHousePhoneNumber.setHint(getResources().getString(R.string.home_number));
        editTextPACINumber.setHint(getResources().getString(R.string.paci_number));
        editTextAddressName.setHint(getResources().getString(R.string.name));
        editTextAddressBlock.setHint(getResources().getString(R.string.block));
        editTextAddressStreet.setHint(getResources().getString(R.string.street));
        editTextAddressAvenue.setHint(getResources().getString(R.string.avenue));
        editTextAddressBuilding.setHint(getResources().getString(R.string.building));
        editTextAddressFloor.setHint(getResources().getString(R.string.floor));
        editTextAddressApartment.setHint(getResources().getString(R.string.apartment_number));
        editTextAddressDirections.setHint(getResources().getString(R.string.directions));
    }
}
