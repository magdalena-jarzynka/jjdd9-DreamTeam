package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.service.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/reservations")
public class ReservationController {

    @Inject
    ReservationService reservationService;

    @POST
    @Path("/add-reservation/{userId}/book/{bookId}")
    public Response addReservation(@PathParam("userId") Long userId,
                                   @PathParam("bookId") Long bookId) {
        reservationService.addReservation(userId, bookId);
        return Response.status(Response.Status.CREATED).build();
    }

}
