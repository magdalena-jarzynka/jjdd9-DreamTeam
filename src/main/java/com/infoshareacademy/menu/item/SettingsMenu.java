package com.infoshareacademy.menu.item;

public enum SettingsMenu {
    CONFIGURATIONS("KONFIGURACJA"),
    SORTING("SORTOWANIE"),
    FORMAT("FORMAT");

    String settingsValue;

    SettingsMenu(String settingsValue){
        this.settingsValue = settingsValue;
    }

    public String getSettingsValue(){
        return settingsValue;
    }
}
