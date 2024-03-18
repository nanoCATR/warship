package com.warship.warship.models;

public class Battle_members {
    private String ID;
    private String BATTLE_NAME, WARSHIP_NAME, STATUS;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBATTLE_NAME() {
        return BATTLE_NAME;
    }

    public void setBATTLE_NAME(String BATTLE_NAME) {
        this.BATTLE_NAME = BATTLE_NAME;
    }

    public String getWARSHIP_NAME() {
        return WARSHIP_NAME;
    }

    public void setWARSHIP_NAME(String WARSHIP_NAME) {
        this.WARSHIP_NAME = WARSHIP_NAME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
