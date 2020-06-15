package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.service.BookService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Inject
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String searchString = req.getParameter("search");
        resp.getWriter().write(bookService.getSearchListJson(searchString));

    }

}
