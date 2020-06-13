package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.BookService;
import com.infoshareacademy.dreamteam.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/favourites")
public class FavouritesServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private ModelInitializer modelInitializer;

//    @Inject
//    private BookService bookService;
//
//    @Inject
//    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        boolean isLoggedIn = Boolean.parseBoolean(String.valueOf(req.getAttribute("isLoggedIn")));
        Map<String, Object> model = modelInitializer.initModel(req);
        if (isLoggedIn) {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "favourites.ftlh");
        } else {
            templatePrinter.printTemplate(resp, model, getServletContext(),
                    "no-access.ftlh");
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        resp.setContentType("text/html; charset=UTF-8");
//        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());
//
//        int startPage = Integer.parseInt(req.getParameter("pageNum")) - 1;
//        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
//        String audio = req.getParameter("audio");
//        String genre = req.getParameter("genre");
//        Map<String, Object> tableData = new HashMap<>();
//        long rows;
//        if ((audio == null || audio.equals("blank")) && (genre == null || genre.equals("blank"))) {
//            tableData.put("books", bookService.findBooks(startPage * pageSize));
//            rows = bookService.countBooks();
//        } else {
//            tableData.put("books", bookService.findBooksByAudioAndGenre(startPage * pageSize, audio, genre));
//            rows = bookService.countBooksByAudioAndGenre(audio, genre);
//        }
//
//        tableData.put("pageNum", startPage + 1);
//
//        long numberOfPages = rows / pageSize;
//        if (numberOfPages % pageSize > 0) {
//            numberOfPages++;
//        }
//
//        tableData.put("genres", bookService.getGenres());
//        tableData.put("numberOfPages", numberOfPages);
//        tableData.put("favourites", userService.getFavourites(Long.parseLong(userContextHolder.getId())));
//        templatePrinter.printTemplate(resp, tableData, getServletContext(), "browse-table.ftlh");
//    }
}
