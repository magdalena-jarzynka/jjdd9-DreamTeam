package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.bean.LeftColumnBean;
import com.infoshareacademy.dreamteam.bean.NavigationBean;
import com.infoshareacademy.dreamteam.context.UserContextHolder;
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
    private LeftColumnBean leftColumnBean;

    @Inject
    private NavigationBean navigationBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");
        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());
        Map<String, Object> manage = new HashMap<>();
        manage.put("name", userContextHolder.getName());
        manage.put("role", userContextHolder.getRole());
        manage.putAll(leftColumnBean.getLeftColumn());
        manage.putAll(navigationBean.getNavigation());
        templatePrinter.printTemplate(resp, manage, getServletContext(), "manage.ftlh");
    }

}
