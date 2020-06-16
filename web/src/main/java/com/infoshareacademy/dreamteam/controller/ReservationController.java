package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.service.ReservationService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/reservations")
public class ReservationController {

    @Inject
    private ReservationService reservationService;

    @POST
    @Path("/users/{userId}/books/{bookId}")
    public Response addReservation(@PathParam("userId") Long userId,
                                   @PathParam("bookId") Long bookId) {
        reservationService.addReservation(userId, bookId);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/users/{userId}/books/{bookId}")
    public Response cancelReservation(@PathParam("userId") Long userId,
                                      @PathParam("bookId") Long bookId) {
        reservationService.delete(userId, bookId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
