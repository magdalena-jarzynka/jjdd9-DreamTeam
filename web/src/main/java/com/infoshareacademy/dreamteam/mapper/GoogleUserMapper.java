package com.infoshareacademy.dreamteam.mapper;

import com.google.api.services.oauth2.model.Userinfoplus;
import com.infoshareacademy.dreamteam.domain.request.UserRequest;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GoogleUserMapper {

    public UserRequest mapGoogleResponseToUserRequest(Userinfoplus info) {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(info.getEmail());
        userRequest.setName(info.getName());
        return userRequest;
    }

}
