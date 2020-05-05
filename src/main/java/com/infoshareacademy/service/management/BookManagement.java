package com.infoshareacademy.service.management;

import com.infoshareacademy.object.*;
import com.infoshareacademy.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class BookManagement {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    public static final String ENTER_TITLE = "Proszę wpisać tytuł.";
    public static final String ENTER_AUTHOR = "Proszę wpisać autora.";
    public static final String ENTER_TRANSLATORS = "Proszę wpisać tłumacza/y.";
    public static final String ENTER_EPOCH = "Proszę wpisać epokę literacką.";
    public static final String ENTER_GENRE = "Proszę wpisać gatunek książki.";
    public static final String ENTER_KIND = "Proszę wpisać gatunek literacki.";
    public static final String ENTER_ISBN = "Proszę wpisać numer ISBN książki.";
    public static final String ENTER_FRAGMENT = "Proszę wpisać fragment książki.";
    public static final String ENTER_MEDIA = "Proszę wpisać nazwę audiobooka jeżeli posiada.";
    private final Scanner scanner = new Scanner(System.in);
    private final Author author = new Author();
    private final Author translator = new Author();
    private final Epoch epoch = new Epoch();
    private final Genre genre = new Genre();
    private final Kind kind = new Kind();
    private final FragmentData fragmentData = new FragmentData();
    private final Media media = new Media();
    private Book book = new Book();
    BookService bookService = new BookService();
    FileWriter fileWriter = new FileWriter();


    public void run(){
        book = setBookDetails();
        add(book);
    }


    private void add(Book book) {
        bookService.findAllBooks().put(getNewKey(bookService.findAllBooks()), book);
        Map<Long, Book> books = bookService.findAllBooks();
        fileWriter.writeToFile(books);
    }


    private long getNewKey(Map<Long, Book> map) {
        return map.entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .get()
                .getKey() + 1;
    }


    private Book setBookDetails() {
        STDOUT.info(ENTER_TITLE);
        book.setTitle(scanner.nextLine());
        STDOUT.info(ENTER_AUTHOR);
        author.setName(scanner.nextLine());
        book.setAuthors(Collections.singletonList(author));
        STDOUT.info(ENTER_TRANSLATORS);
        translator.setName(scanner.nextLine());
        book.setTranslators(Collections.singletonList(translator));
        STDOUT.info(ENTER_EPOCH);
        epoch.setName(scanner.nextLine());
        book.setEpochs(Collections.singletonList(epoch));
        STDOUT.info(ENTER_GENRE);
        genre.setName(scanner.nextLine());
        book.setGenres(Collections.singletonList(genre));
        STDOUT.info(ENTER_KIND);
        kind.setName(scanner.nextLine());
        book.setKinds(Collections.singletonList(kind));
        STDOUT.info(ENTER_ISBN);
        book.setIsbnPdf(scanner.nextLine());
        STDOUT.info(ENTER_FRAGMENT);
        fragmentData.setHtml(scanner.nextLine());
        book.setBookFragment(fragmentData);
        STDOUT.info(ENTER_MEDIA);
        media.setName(scanner.nextLine());
        book.setMedia(Collections.singletonList(media));
        return book;
    }

}


//STDOUT.info("1.Autor: {}\n2.Tytuł:  {}\n3.Tłumacze: {}\n 4.Epoka:" +
//                      "  {}\n5.Gatunek: {}\n6.Gatunek literacki:  {}\n7.ISBN:" +
//                        "  {}\n8.Fragment ksiązki:   {}\n9.Media: {}",
//                book.getTitle(), book.getAuthors().get(0).getName(), book.getTranslators()
//                        .get(0).getName(), book.getEpochs().get(0).getName(), book.getGenres().get(0).getName(),
//                book.getKinds().get(0).getName(), book.getIsbnPdf(),
//                book.getBookFragment().getFragment(), book.getMedia().get(0).getName());

//private long getNewKey() {
//        return bookService.findAllBooks().entrySet()
//                .stream()
//                .max(Map.Entry.comparingByKey())
//                .get()
//                .getKey() + 1;
//
//    }


