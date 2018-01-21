package com.mawaqaa.sahalath.aacustomer.Data;

import android.util.Log;

/**
 * Created by anson on 4/17/2017.
 */

public class CommunicationCompany {
    public String companyId, companyName, languageId;
    public boolean isActive;

    public CommunicationCompany(String companyId, String companyName, String languageId, boolean isActive) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.languageId = languageId;
        this.isActive = isActive;
    }

    public CommunicationCompany(String companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
        Log.e("CompanyData","name"+companyName+"companyId"+companyId);
    }
}
