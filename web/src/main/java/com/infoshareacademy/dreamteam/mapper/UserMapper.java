package com.infoshareacademy.dreamteam.mapper;

import com.infoshareacademy.dreamteam.domain.entity.User;
import com.infoshareacademy.dreamteam.domain.view.UserView;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserMapper {

    public UserView mapEntityToView(User user) {
        UserView userView = new UserView();
        userView.setEmail(user.getEmail());
        userView.setId(user.getId());
        userView.setName(user.getName());
        userView.setRole(user.getRole().getName().toString());
        return userView;
    }
}
