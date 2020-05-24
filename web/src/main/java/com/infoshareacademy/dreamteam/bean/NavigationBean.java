package com.infoshareacademy.dreamteam.bean;

import com.infoshareacademy.dreamteam.cdi.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class NavigationBean {

    @Inject
    private User user;

    private Map<String, Object> navigation = new HashMap<>();

    @PostConstruct
    public void init() {
        if (user.isLoggedIn()) {
            navigation.put("logIn", NavigationTag.LOG_IN.getTagDescription());
        } else{
            navigation.put("logOut", NavigationTag.LOG_OUT.getTagDescription());
        }
    }
    public Map<String, Object> getNavigation() {
        return navigation;
    }
}
