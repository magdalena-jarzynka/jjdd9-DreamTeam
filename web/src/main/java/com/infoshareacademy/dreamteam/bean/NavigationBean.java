package com.infoshareacademy.dreamteam.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class NavigationBean {

    private Map<String, Object> navigation = new HashMap<>();

    @PostConstruct
    public void init() {
        navigation.put("logIn", NavigationTag.LOG_IN.getTagDescription());
        navigation.put("logOut", NavigationTag.LOG_OUT.getTagDescription());
    }

    public Map<String, Object> getNavigation() {
        return navigation;
    }
}
