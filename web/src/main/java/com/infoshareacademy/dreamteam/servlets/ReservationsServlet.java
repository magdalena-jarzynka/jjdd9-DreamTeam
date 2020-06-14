package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.domain.view.UserView;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.ReservationService;
import com.infoshareacademy.dreamteam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        resp.setContentType("text/html; charset=UTF-8");
        boolean isLoggedIn = Boolean.parseBoolean(String.valueOf(req.getAttribute("isLoggedIn")));
        Map<String, Object> model = modelInitializer.initModel(req);

        if (isLoggedIn) {
            String id = String.valueOf(req.getSession().getAttribute("id"));
            long userId = 0L;
            try {
                userId = Long.parseLong(id);
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
            }
            model.put("userId", userId);
            UserView userView = userService.findUserViewById(userId);
            List<ReservationView> reservations = reservationService.findReservationsByUser(userView);
            List<ReservationView> confirmedReservations = new ArrayList<>();
            for (ReservationView reservationView : reservations) {
                if (reservationView.getConfirmed()) {
                    confirmedReservations.add(reservationView);
                }
            }
            model.put("userReservations", confirmedReservations);

            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "reservations.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }
    }

}