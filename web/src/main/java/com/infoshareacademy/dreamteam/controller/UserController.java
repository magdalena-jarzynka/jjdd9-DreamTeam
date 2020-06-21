package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserController {

    @Inject
    UserService userService;

    @PATCH
    @Path("/{userId}")
    public Response addReservation(@PathParam("userId") Long userId) {
        userService.changeUserAccessLevel(userService.findUserViewById(userId));
        return Response.status(Response.Status.ACCEPTED).build();
    }

}
