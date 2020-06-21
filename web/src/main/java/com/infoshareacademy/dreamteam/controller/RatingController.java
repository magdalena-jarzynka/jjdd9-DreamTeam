package com.infoshareacademy.dreamteam.controller;

import com.infoshareacademy.dreamteam.service.RatingService;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Optional;

@Path("/rating")
public class RatingController {

    @Inject
    private RatingService ratingService;

    @POST
    @Path("/books/{bookId}/rating/{rate}")
    public Response addRating(@PathParam("bookId") Long bookId,
                              @PathParam("rate") int rate,
                              @Context HttpServletRequest req,
                              @Context HttpServletResponse res) {

        String ip = req.getRemoteAddr();
        if (readCookie("ip", req).isEmpty()) {
            ratingService.addRating(bookId, rate);
            Cookie cookie = new Cookie("ip", ip);
            res.addCookie(cookie);
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private Optional<String> readCookie(String key, HttpServletRequest req) {
        return Arrays.stream(req.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

}
