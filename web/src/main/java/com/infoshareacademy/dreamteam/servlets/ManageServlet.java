package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;

import com.infoshareacademy.dreamteam.service.LoadJsonService;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/manage")
@MultipartConfig
public class ManageServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Inject
    LoadJsonService loadJsonService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        boolean isAdmin = Boolean.parseBoolean(String.valueOf(req.getAttribute("isAdmin")));
        Map<String, Object> model = modelInitializer.initModel(req);
        if (isAdmin) {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "manage.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }
    }
  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, Object> manage = new HashMap<>();
        manage.put("user", user);
        manage.putAll(leftColumnBean.getLeftColumn());
        templatePrinter.printTemplate(resp, manage, getServletContext(), "manage.ftlh");

        Part part = req.getPart("json");
        loadJsonService.loadFromJson(part);
    }
}
