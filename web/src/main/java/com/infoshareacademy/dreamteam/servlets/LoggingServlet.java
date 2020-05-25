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
    private static final Logger loggerApp = LoggerFactory.getLogger(LoggingServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        loggerApp.debug("Server debug \n");
        loggerApp.info("Server info \n");
        loggerApp.warn("Server warn \n");
        loggerApp.error("Server error \n");

        loggerApp.debug("Application file debug \n");
        loggerApp.info("Application file info \n");
        loggerApp.warn("Application file warn \n");
        loggerApp.error("Application file error \n");

        PrintWriter writer = resp.getWriter();
        writer.println("test");

    }
}
