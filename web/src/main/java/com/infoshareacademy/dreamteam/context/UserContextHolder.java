package com.infoshareacademy.dreamteam.context;

import com.infoshareacademy.dreamteam.cdi.Role;
import com.infoshareacademy.dreamteam.domain.view.UserView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UserContextHolder {
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private HttpSession httpSession;

    public UserContextHolder(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public String getName() {
        return (String) httpSession.getAttribute(NAME);
    }

    public String getRole() {
        return Optional.ofNullable((String) httpSession.getAttribute(ROLE)).orElse(String.valueOf(Role.GUEST));
    }

    public void setContext(UserView userView) {
        httpSession.setAttribute("name", userView.getName());
        httpSession.setAttribute("email", userView.getEmail());
        httpSession.setAttribute("id", userView.getId());
        httpSession.setAttribute("role", userView.getRole());
    }

}
