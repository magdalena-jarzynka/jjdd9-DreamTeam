package com.infoshareacademy.dreamteam.bean;

public enum NavigationTag {
    LOG_IN("ZALOGUJ"),
    LOG_OUT("WYLOGUJ");

    private String tagDescription;

    NavigationTag(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public String getTagDescription() {
        return tagDescription;
    }

}
