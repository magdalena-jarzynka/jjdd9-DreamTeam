package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.domain.api.dto.BookDto;
import com.infoshareacademy.dreamteam.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/manage")
public class ManageController {

    @Inject
    private BookService bookService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(BookDto bookDto) {
        bookService.saveBookDto(bookDto);
        return Response.status(Response.Status.CREATED).build();
    }
}
