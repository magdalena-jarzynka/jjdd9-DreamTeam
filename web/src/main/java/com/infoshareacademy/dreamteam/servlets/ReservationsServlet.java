//package com.infoshareacademy.dreamteam.servlets;
//
//import com.infoshareacademy.dreamteam.context.UserContextHolder;
//import com.infoshareacademy.dreamteam.domain.view.ReservationView;
//import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
//import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
//import com.infoshareacademy.dreamteam.service.ReservationService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.ejb.EJB;
//import javax.inject.Inject;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet("/reservations")
//public class ReservationsServlet extends HttpServlet {
//    private static final Logger logger = LoggerFactory.getLogger(ReservationsServlet.class);
//
//    @Inject
//    ReservationService reservationService;
//
//    @Inject
//    private TemplatePrinter templatePrinter;
//
//    @Inject
//    private ModelInitializer modelInitializer;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        resp.setContentType("text/html; charset=UTF-8");
//        boolean isLoggedIn = Boolean.parseBoolean(String.valueOf(req.getAttribute("isLoggedIn")));
//        Map<String, Object> model = modelInitializer.initModel(req);
//        if (!isLoggedIn) {
//            templatePrinter.printTemplate(resp, model, getServletContext(),
//                    "no-access.ftlh");
//        } else {
//            UserContextHolder userContextHolder = new UserContextHolder(req.getSession());
//            Long userId = userContextHolder.getId();
//            List<ReservationView> reservations = ;
//
//
////            String param = req.getParameter("id");
////            long reservationId = 0;
////            try {
////                reservationId = Long.parseLong(param);
////            } catch (NumberFormatException e) {
////                logger.error(e.getMessage());
////            }
//            templatePrinter.printTemplate(resp, model, getServletContext(),
//                    "reservations.ftlh");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//    }
//}
