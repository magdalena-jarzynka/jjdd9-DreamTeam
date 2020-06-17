package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> model = modelInitializer.initModel(req);
        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());

        if (userContextHolder.isAuthenticated()) {
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