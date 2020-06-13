package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.domain.view.ReservationView;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.ReservationService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

@WebServlet("/confirm")
public class ReservationConfirmServlet extends HttpServlet {

    public static final String CONFIRMATION_FAILURE = "Bardzo nam przykro, ale rezerwacja książki wygasła. \n" +
            "Pamiętaj, że na potwierdzenie masz dokładnie 15 minut.";
    public static final String CONFIRMATION_SUCCESS = "Twoja rezerwacja została poprawnie potwierdzona. \n" +
            "Twoja rezerwacja już powinna być widoczna w panelu REZERWACJE.";
    @Inject
    ReservationService reservationService;

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Map<String, Object> model = modelInitializer.initModel(req);
        String reservationToken = req.getParameter("token");
        try {
            ReservationView reservation = reservationService.findReservationViewByToken(reservationToken);
            boolean isSuccessfullyConfirmed = reservationService.confirmReservation(reservation);
            String confirmationResult = CONFIRMATION_FAILURE;
            if (isSuccessfullyConfirmed) {
                confirmationResult = CONFIRMATION_SUCCESS;
            }
            String bookTitle = reservation.getBookView().getTitle();
            model.put("result", confirmationResult);
            model.put("title", bookTitle);
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "confirmation.ftlh");
        } catch (NoSuchElementException e) {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "wrong-token.ftlh");
        }

    }
}
