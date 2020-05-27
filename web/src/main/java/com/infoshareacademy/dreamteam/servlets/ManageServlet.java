package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.bean.LeftColumnBean;
import com.infoshareacademy.dreamteam.cdi.User;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/manage")
public class ManageServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private User user;

    @Inject
    private LeftColumnBean leftColumnBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, Object> manage = new HashMap<>();
        manage.put("user", user);
        manage.putAll(leftColumnBean.getLeftColumn());
        templatePrinter.printTemplate(resp, manage, getServletContext(), "manage.ftlh");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");

        Map<String, Object> manage = new HashMap<>();
        manage.put("user", user);
        manage.putAll(leftColumnBean.getLeftColumn());
        templatePrinter.printTemplate(resp, manage, getServletContext(), "manage.ftlh");

        req.getParameter("json");
    }
}
