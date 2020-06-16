package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.ReservationService;
import com.infoshareacademy.dreamteam.service.UserService;
import com.infoshareacademy.dreamteam.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/reservations")
public class ReservationsServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ReservationsServlet.class);


    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Inject
    private ReservationService reservationService;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isLoggedIn = Boolean.parseBoolean(String.valueOf(req.getAttribute("isLoggedIn")));
        Map<String, Object> model = modelInitializer.initModel(req);
        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());

        if (isLoggedIn && ValidationService.validateIfParsableToLong(userContextHolder.getId())) {

            long userId = Long.parseLong(userContextHolder.getId());
            model.put("userId", userId);
            model.put("userReservations", reservationService.findConfirmedReservationsByUser(userId));

            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "reservations.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }
    }

}