package com.infoshareacademy.dreamteam.model;

import com.infoshareacademy.dreamteam.layout.builder.LeftColumnBuilder;
import com.infoshareacademy.dreamteam.layout.builder.NavigationBuilder;
import com.infoshareacademy.dreamteam.context.UserContextHolder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class ModelInitializer {
    public static final String NAME = "name";
    public static final String ROLE = "role";

    @Inject
    private LeftColumnBuilder leftColumnBuilder;

    @Inject
    private NavigationBuilder navigationBean;

    public Map<String, Object> initModel(HttpServletRequest httpServletRequest) {
        final Map<String, Object> model = new HashMap<>();
        initUserContext(httpServletRequest.getSession(), model);
        initLayOut(model, httpServletRequest);
        return model;
    }

    private void initUserContext(HttpSession httpSession, Map<String, Object> model) {
        UserContextHolder userContextHolder = new UserContextHolder(httpSession);
        model.put(NAME, userContextHolder.getName());
        model.put(ROLE, userContextHolder.getRole());
    }

    private void initLayOut(Map<String, Object> model, HttpServletRequest httpServletRequest) {
        model.put(httpServletRequest.getHttpServletMapping().getMatchValue() + "_active", "active");
        model.putAll(leftColumnBuilder.build());
        model.putAll(navigationBean.build());
    }

}
