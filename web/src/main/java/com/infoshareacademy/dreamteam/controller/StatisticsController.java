package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.service.AuthorService;
import com.infoshareacademy.dreamteam.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/stats")
@Produces(MediaType.APPLICATION_JSON)
public class StatisticsController {

    @Inject
    AuthorService authorService;

    @Inject
    BookService bookService;

    @GET
    @Path("/author")
    public Response getMostBorrowedAuthors() {
        return Response.status(Response.Status.OK).entity(authorService.findAuthorReservationCount()).build();
    }

    @GET
    @Path("/book")
    public Response getMostBorrowedBooks() {
        return Response.status(Response.Status.OK).entity(bookService.findBookReservationCount()).build();
    }
}
