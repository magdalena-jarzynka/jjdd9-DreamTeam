package com.infoshareacademy.dreamteam.servlets;

import com.infoshareacademy.dreamteam.context.UserContextHolder;
import com.infoshareacademy.dreamteam.domain.entity.Book;
import com.infoshareacademy.dreamteam.freemarker.TemplatePrinter;
import com.infoshareacademy.dreamteam.initializer.ModelInitializer;
import com.infoshareacademy.dreamteam.service.BookService;
import com.infoshareacademy.dreamteam.service.UserService;
import com.infoshareacademy.dreamteam.service.ValidationService;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/browse")
public class BrowseServlet extends HttpServlet {

    @Inject
    private TemplatePrinter templatePrinter;

    @Inject
    private BookService bookService;

    @Inject
    private ModelInitializer modelInitializer;

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        Map<String, Object> model = modelInitializer.initModel(req);
        templatePrinter.printTemplate(resp, model, getServletContext(),
                "browse.ftlh");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        UserContextHolder userContextHolder = new UserContextHolder(req.getSession());

        int startPage = Integer.parseInt(req.getParameter("pageNum")) - 1;
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String audio = req.getParameter("audio");
        String genre = req.getParameter("genre");
        String search = req.getParameter("search");
        String userId = userContextHolder.getId();

        Map<String, Object> tableData = new HashMap<>();
        long rows;

        if ((audio == null || audio.equals("blank"))
                && (genre == null || genre.equals("blank"))
                && (search == null || search.isEmpty())) {
            tableData.put("books", bookService.findBooks(startPage * pageSize));
            rows = bookService.countBooks();
        } else {
            tableData.put("books", bookService.findBooksByAudioAndGenreAndStringOfCharacters(startPage * pageSize, audio, genre, search));
            rows = bookService.countBooksByAudioAndGenreAndStringOfCharacters(audio, genre, search);
        }

        tableData.put("pageNum", startPage + 1);

        long numberOfPages = rows / pageSize;
        if (numberOfPages % pageSize > 0) {
            numberOfPages++;
        }

        tableData.put("genres", bookService.getGenres());
        tableData.put("numberOfPages", numberOfPages);
        tableData.put("userRole", userContextHolder.getRole());
        if(ValidationService.validate(userId)) {
            Long userIdLong = Long.parseLong(userId);
            tableData.put("userId", userIdLong);
        tableData.put("favourites", userService
                .getFavourites(userIdLong).stream()
                .map(Book::getId).collect(Collectors.toList()));
        }
        templatePrinter.printTemplate(resp, tableData, getServletContext(), "browse-table.ftlh");
    }
}
