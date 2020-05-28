package com.infoshareacademy.dreamteam.layout.builder;

import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class NavigationBuilder {

    public Map<String, Object> build() {
        Map<String, Object> navigation = new HashMap<>();
        navigation.put("logIn", NavigationList.LOG_IN.getLabel());
        navigation.put("logOut", NavigationList.LOG_OUT.getLabel());
        return navigation;
    }

}
