package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet("/favourites")
public class FavouritesServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> model = modelInitializer.initModel(req);
        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());

        if (userContextHolder.isAuthenticated()) {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "favourites.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }
    }

}
