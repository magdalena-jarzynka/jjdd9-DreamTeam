package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.domain.request.ReservationRequest;
import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.service.BookService;
import com.infoshareacademy.dreamteam.service.ReservationService;
import com.infoshareacademy.dreamteam.service.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/reservations")
public class ReservationController {

    @Inject
    ReservationService reservationService;

    @Inject
    BookService bookService;

    @Inject
    UserService userService;

    @POST
    @Path("/add-reservation/{userId}/book/{bookId}")
    public Response addReservation(@PathParam("userId") Long userId,
                                          @PathParam("bookId") Long bookId) {

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setBookView(bookService.findBookById(bookId));
        reservationRequest.setUserView(userService.findUserViewById(userId));
        reservationService.addReservation(reservationRequest);

        return Response.status(Response.Status.CREATED).build();
    }

}