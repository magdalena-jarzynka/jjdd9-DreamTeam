package com.infoshareacademy.dreamteam.servlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LogOutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("email");
        logger.info("User with email: {} logged out", email);
        req.getSession().removeAttribute("email");
        req.getSession().removeAttribute("name");
        req.getSession().removeAttribute("role");
        resp.sendRedirect("/");
    }

}
