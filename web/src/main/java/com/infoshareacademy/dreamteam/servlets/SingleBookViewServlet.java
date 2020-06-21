package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.domain.entity.Rating;
import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.BookService;
import com.infoshareacademy.dreamteam.service.RatingService;
import com.infoshareacademy.dreamteam.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet("/single")
public class SingleBookViewServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SingleBookViewServlet.class);

    @EJB
    private BookService bookService;

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Inject
    private RatingService ratingService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        Map<String, Object> model = modelInitializer.initModel(req);
        String bookIdParameter = req.getParameter("id");

        if (ValidationService.validate(bookIdParameter)) {
            BookView bookView = bookService.findBookViewById(Long.parseLong(bookIdParameter));
            model.put("book", bookView);
            model.put("reserved", !bookView.getReservationViews().isEmpty());
            Rating rating = ratingService.findByBookId(bookView.getId());
            model.put("average", ratingService.calculateAverageRating(rating));
        }

        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());
        model.put("userRole", userContextHolder.getRole());

        if (ValidationService.validate(userContextHolder.getId())) {
            model.put("userId", Long.parseLong(userContextHolder.getId()));
        }

        templatePrinter.printTemplate(resp, model, getServletContext(),
                "single-book-view.ftlh");

    }

}
