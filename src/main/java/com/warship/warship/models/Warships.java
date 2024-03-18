package com.warship.warship.models;

public class Warships {


    private Integer ID;
    private String NAME;
    private String CLASS, COMISSION_DATE, DECOMISSION_DATE;

    public String getNAME() {
        return NAME;
    }
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getCOMISSION_DATE() {
        return COMISSION_DATE;
    }

    public void setCOMISSION_DATE(String COMISSION_DATE) {
        this.COMISSION_DATE = COMISSION_DATE;
    }

    public String getDECOMISSION_DATE() {
        return DECOMISSION_DATE;
    }

    public void setDECOMISSION_DATE(String DECOMISSION_DATE) {
        this.DECOMISSION_DATE = DECOMISSION_DATE;
    }
}
