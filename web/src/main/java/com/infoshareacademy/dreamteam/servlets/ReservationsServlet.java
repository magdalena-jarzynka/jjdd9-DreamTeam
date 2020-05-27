package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.model.ModelInitializer;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/reservations")
public class ReservationsServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        boolean isLoggedIn = Boolean.parseBoolean(String.valueOf(req.getAttribute("isLoggedIn")));
        Map<String, Object> model = modelInitializer.initModel(req);
        if (isLoggedIn) {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "reservations.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }
    }

}
