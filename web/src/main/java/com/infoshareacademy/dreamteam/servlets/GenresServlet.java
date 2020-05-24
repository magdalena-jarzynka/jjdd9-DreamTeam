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

@WebServlet("/genres")
public class GenresServlet extends HttpServlet {

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

        if (req.getSession().getAttribute("role") == null) {
            user.setRole(Role.GUEST);
        } else {
            user.setRole((Role) req.getSession().getAttribute("role"));
            user.setName((String) req.getSession().getAttribute("name"));
            user.setLoggedIn(true);
        }

        Map<String, Object> genres = new HashMap<>();
        genres.put("user", user);
        genres.putAll(leftColumnBean.getLeftColumn());
        genres.putAll(navigationBean.getNavigation());
        templatePrinter.printTemplate(resp, genres, getServletContext(), "genres.ftlh");
    }
}


