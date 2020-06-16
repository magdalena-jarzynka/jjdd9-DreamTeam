package com.infoshareacademy.dreamteam.controller;


import com.infoshareacademy.dreamteam.service.BookService;
import com.infoshareacademy.dreamteam.service.FavouritesService;
import com.infoshareacademy.dreamteam.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class FavouritesController {

    @Inject
    private BookService bookService;

    @Inject
    private UserService userService;

    @Inject
    private FavouritesService favouritesService;

    @POST
    @Path("users/{userId}/favourites/{bookId}")
    public Response addToFavourites(@PathParam("userId") Long userId,
                                    @PathParam("bookId") Long bookId) {

    favouritesService.addBookToFavourites(bookId, userId);
    return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("users/{userId}/favourites/{bookId}")
    public Response RemoveFavourites(@PathParam("userId") Long userId,
                                    @PathParam("bookId") Long bookId) {

        favouritesService.removeBookFromFavourites(bookId, userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
