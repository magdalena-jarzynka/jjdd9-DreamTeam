package com.infoshareacademy.dreamteam.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logging-servlet")
public class LoggingServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoggingServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        logger.debug("Server debug \n");
        logger.info("Server info \n");
        logger.warn("Server warn \n");
        logger.error("Server error \n");

        PrintWriter writer = resp.getWriter();
        writer.println(req.getSession().getAttribute("name"));

    }
}
