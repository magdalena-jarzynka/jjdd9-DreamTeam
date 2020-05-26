package com.infoshareacademy.dreamteam.context;

import com.infoshareacademy.dreamteam.cdi.Role;
import com.infoshareacademy.dreamteam.domain.view.UserView;

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

    public UserContextHolder(HttpSession httpSession, UserView userView){
        this.httpSession = httpSession;
        setContext(userView);
    }

    public String getName() {
        return (String) httpSession.getAttribute(NAME);
    }

    public String getRole() {
        return Optional.ofNullable((String) httpSession.getAttribute(ROLE)).orElse(String.valueOf(Role.GUEST));
    }

    private void setContext(UserView userView) {
        httpSession.setAttribute(NAME, userView.getName());
        httpSession.setAttribute(EMAIL, userView.getEmail());
        httpSession.setAttribute(ID, userView.getId());
        httpSession.setAttribute(ROLE, userView.getRole());
    }

    public void invalidate(){
        httpSession.removeAttribute(EMAIL);
        httpSession.removeAttribute(NAME);
        httpSession.removeAttribute(ROLE);
    }

}
