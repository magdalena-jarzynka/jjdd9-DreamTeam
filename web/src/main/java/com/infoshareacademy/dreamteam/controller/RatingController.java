package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.service.RatingService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/rating")
public class RatingController {

    @Inject
    private RatingService ratingService;

    @POST
    @Path("/books/{bookId}")
    public Response addRating(@PathParam("bookId") Long bookId) {
        ratingService.addRating(bookId);
        return Response.status(Response.Status.CREATED).build();
    }

}
