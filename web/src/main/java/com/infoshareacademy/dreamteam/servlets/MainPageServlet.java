package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.bean.LeftColumnBean;
import com.infoshareacademy.dreamteam.bean.NavigationBean;
import com.infoshareacademy.dreamteam.cdi.Role;
import com.infoshareacademy.dreamteam.cdi.User;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("")
public class MainPageServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private User user;

    @Inject
    private LeftColumnBean leftColumnBean;

    @Inject
    private NavigationBean navigationBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");

        if(req.getSession().getAttribute("role") == null){
            user.setRole(Role.GUEST);
        } else{
            user.setRole((Role) req.getSession().getAttribute("role"));
            user.setLoggedIn(true);
        }

        Map<String, Object> mainPage = new HashMap<>();
        mainPage.put("user", user);
        mainPage.putAll(leftColumnBean.getLeftColumn());
        mainPage.putAll(navigationBean.getNavigation());
        templatePrinter.printTemplate(resp, mainPage, getServletContext(), "main-page.ftlh");
    }
}


