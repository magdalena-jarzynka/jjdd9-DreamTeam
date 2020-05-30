package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.domain.view.BookView;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Map<String, Object> model = modelInitializer.initModel(req);
        long bookId = 1L;
        try {
            bookId = Long.parseLong(req.getParameter("bookId"));
        } catch (NumberFormatException e) {
            logger.error("Book id not found.");
        }

        BookView bookView = bookService.findBookById(bookId);
        model.put("book", bookView);

        templatePrinter.printTemplate(resp, model, getServletContext(),
                "single-book-view.ftlh");
    }

}
