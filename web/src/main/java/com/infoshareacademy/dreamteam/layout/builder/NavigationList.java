package com.infoshareacademy.dreamteam.layout.builder;

public enum NavigationList {
    LOG_IN("ZALOGUJ"),
    LOG_OUT("WYLOGUJ");

    private String label;

    NavigationList(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
