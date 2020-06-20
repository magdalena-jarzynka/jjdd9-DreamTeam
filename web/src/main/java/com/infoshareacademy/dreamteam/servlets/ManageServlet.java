package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.LoadDatabaseService;
import com.infoshareacademy.dreamteam.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;

@WebServlet("/manage")
@MultipartConfig
public class ManageServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Inject
    LoadDatabaseService loadDatabaseService;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        
        boolean isAdmin = Boolean.parseBoolean(String.valueOf(req.getAttribute("isAdmin")));
        Map<String, Object> model = modelInitializer.initModel(req);
        if (isAdmin) {
            model.put("users", userService.findAll());
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "manage.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        boolean isAdmin = Boolean.parseBoolean(String.valueOf(req.getAttribute("isAdmin")));
        Map<String, Object> model = modelInitializer.initModel(req);
        if (isAdmin) {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "manage.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }

        Part part = req.getPart("json");

        loadDatabaseService.loadFromJson(part);
    }
}
