package com.infoshareacademy.dreamteam.domain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookUrl {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
