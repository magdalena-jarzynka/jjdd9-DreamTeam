package com.infoshareacademy.dreamteam.controller;


import com.infoshareacademy.dreamteam.service.FavouritesService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/favourites")
public class FavouritesController {

    @Inject
    private FavouritesService favouritesService;

    @PUT
    @Path("/users/{userId}/books/{bookId}")
    public Response addToFavourites(@PathParam("userId") Long userId,
                                    @PathParam("bookId") Long bookId) {

        favouritesService.addBookToFavourites(bookId, userId);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/users/{userId}/books/{bookId}")
    public Response RemoveFavourites(@PathParam("userId") Long userId,
                                     @PathParam("bookId") Long bookId) {

        favouritesService.removeBookFromFavourites(bookId, userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
