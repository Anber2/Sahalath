package com.mawaqaa.sahalath.aacustomer.Data;

/**
 * Created by anson on 4/18/2017.
 */

public class OccupationData {
    public String occupationId, occupationName, languageId;
    public boolean isActive;

    public OccupationData(String occupationId, String occupationName, String languageId, boolean isActive) {
        this.occupationId = occupationId;
        this.occupationName = occupationName;
        this.languageId = languageId;
        this.isActive = isActive;
    }

    public OccupationData(String occupationId, String occupationName) {
        this.occupationId = occupationId;
        this.occupationName = occupationName;

    }
}
