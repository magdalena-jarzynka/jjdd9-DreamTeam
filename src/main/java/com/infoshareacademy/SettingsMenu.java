package com.infoshareacademy;

public enum SettingsMenu {
    CONFIGURATIONS("KONFIGURACJA"),
    SORTING("SORTOWANIE"),
    FORMAT("FORMAT");

    String settingsValue;

    SettingsMenu(String sv){
        settingsValue = sv;
    }

    public String getSettingsValue(){
        return settingsValue;
    }
}
