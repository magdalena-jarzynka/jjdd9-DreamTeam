package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.BookService;
import com.infoshareacademy.dreamteam.service.UserService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/favourites")
public class FavouritesServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

    @Inject
    private BookService bookService;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> model = modelInitializer.initModel(req);
        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());

        if (userContextHolder.isAuthenticated()) {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "favourites.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");
        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());

        int startPage = Integer.parseInt(req.getParameter("pageNum")) - 1;
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String userRole = userContextHolder.getRole();
        Long userId = userContextHolder.getIdValue();
        Map<String, Object> tableData = new HashMap<>();
        long rows;
        tableData.put("books", userService.getFavourites(userId));
        rows = bookService.countBooks();
        tableData.put("pageNum", startPage + 1);

        long numberOfPages = rows / pageSize;
        if (numberOfPages % pageSize > 0) {
            numberOfPages++;
        }

        tableData.put("genres", bookService.getGenres());
        tableData.put("numberOfPages", numberOfPages);
        tableData.put("userRole", userRole);
        tableData.put("userId", userId);
        tableData.put("favourites", userService
                .getFavourites(userId).stream()
                .map(Book::getId).collect(Collectors.toList()));
        templatePrinter.printTemplate(resp, tableData, getServletContext(), "browse-table.ftlh");
    }
}
