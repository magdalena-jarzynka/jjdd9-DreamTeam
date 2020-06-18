package com.infoshareacademy.dreamteam.context;

import com.infoshareacademy.dreamteam.domain.view.UserView;
import com.infoshareacademy.dreamteam.role.RoleType;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UserContextHolder {
    private static final String NAME = "name";
    private static final String ROLE = "role";
    public static final String EMAIL = "email";
    public static final String ID = "id";
    private final HttpSession httpSession;

    public UserContextHolder(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public UserContextHolder(HttpSession httpSession, UserView userView) {
        this.httpSession = httpSession;
        setContext(userView);
    }

    public String getName() {
        return (String) httpSession.getAttribute(NAME);
    }

    public Long getId() {
        return (Long) httpSession.getAttribute(ID);
    }

    public String getRole() {
        return Optional.ofNullable((String) httpSession.getAttribute(ROLE)).orElse(String.valueOf(RoleType.GUEST));
    }

    public String getId() {
        return (String.valueOf(httpSession.getAttribute(ID)));
    }

    private void setContext(UserView userView) {
        httpSession.setAttribute(NAME, userView.getName());
        httpSession.setAttribute(EMAIL, userView.getEmail());
        httpSession.setAttribute(ID, userView.getId());
        httpSession.setAttribute(ROLE, userView.getRole());
    }

    public void invalidate() {
        httpSession.removeAttribute(EMAIL);
        httpSession.removeAttribute(NAME);
        httpSession.removeAttribute(ROLE);
        httpSession.removeAttribute(ID);
    }

    public Boolean isAuthenticated(){
        return getName() != null;
    }

}
