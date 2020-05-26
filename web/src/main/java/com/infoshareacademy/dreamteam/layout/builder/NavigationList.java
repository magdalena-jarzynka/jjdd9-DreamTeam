package com.infoshareacademy.dreamteam.layout.builder;

public enum NavigationList {
    LOG_IN("ZALOGUJ"),
    LOG_OUT("WYLOGUJ");

    private String label;

    NavigationList(String tagDescription) {
        this.label = tagDescription;
    }

    public String getLabel() {
        return label;
    }

}
