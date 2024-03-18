package com.warship.warship.models;

import java.sql.Date;

public class Battles {
    private String BATTLE_NAME;
    private String BATTLE_DATE;

    public String getBATTLE_NAME() {
        return BATTLE_NAME;
    }

    public void setBATTLE_NAME(String BATTLE_NAME) {
        this.BATTLE_NAME = BATTLE_NAME;
    }

    public String getBATTLE_DATE() {
        return BATTLE_DATE;
    }

    public void setBATTLE_DATE(String BATTLE_DATE) {
        this.BATTLE_DATE = BATTLE_DATE;
    }
}
