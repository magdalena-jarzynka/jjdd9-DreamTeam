package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.model.ModelInitializer;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet("")
public class MainPageServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");
        Map<String, Object> model = modelInitializer.initModel(req);
        templatePrinter.printTemplate(resp, model, getServletContext(),
                "main-page.ftlh");
    }

}
