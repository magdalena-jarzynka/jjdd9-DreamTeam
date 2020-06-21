package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.UserService;
import com.infoshareacademy.dreamteam.service.ValidationService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/favourites" )
public class FavouritesServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> model = modelInitializer.initModel(req);
        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());

        String userRole = userContextHolder.getRole();
        String userId = userContextHolder.getId();
        Long userIdLong;
        if (ValidationService.validate(userId)) {
            userIdLong = Long.parseLong(userId);
        } else {
            return;
        }

        model.put("books", userService.getFavourites(userIdLong));
        model.put("userRole", userRole);
        model.put("userId", userId);
        model.put("favouritesId", userService
                .getFavourites(userIdLong).stream()
                .map(Book::getId).collect(Collectors.toList()));

        if (userContextHolder.isAuthenticated()) {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "favourites.ftlh" );
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh" );
        }
    }
}
