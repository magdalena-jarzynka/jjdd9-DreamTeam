package com.infoshareacademy.service.management;

import com.infoshareacademy.object.*;
import com.infoshareacademy.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.infoshareacademy.input.UserInputService.getUserInput;
import static com.infoshareacademy.menu.MenuUtils.cleanTerminal;

public class BookManagement {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String ENTER_TITLE = "Proszę wpisać tytuł: ";
    private static final String ENTER_AUTHOR = "Proszę wpisać autora: ";
    private static final String ENTER_TRANSLATORS = "Proszę wpisać tłumacza/y: ";
    private static final String ENTER_EPOCH = "Proszę wpisać epokę literacką: ";
    private static final String ENTER_GENRE = "Proszę wpisać gatunek książki: ";
    private static final String ENTER_KIND = "Proszę wpisać gatunek literacki: ";
    private static final String ENTER_ISBN = "Proszę wpisać numer ISBN książki: ";
    private static final String ENTER_FRAGMENT = "Proszę wpisać fragment książki: ";
    private static final String ENTER_MEDIA = "Proszę wpisać nazwę audiobooka jeżeli posiada: ";
    private static final String BOOK_ADDED = "\n\nZakończono dodawanie pozycji, wciśnij ENTER aby wrócić do poprzedniego widoku.\n\n";
    private final Scanner scanner = new Scanner(System.in);
    private final Author author = new Author();
    private final Author translator = new Author();
    private final Epoch epoch = new Epoch();
    private final Genre genre = new Genre();
    private final Kind kind = new Kind();
    private final FragmentData fragmentData = new FragmentData();
    private final Media media = new Media();
    private Book book = new Book();
    private BookService bookService = new BookService();
    private FileWriter fileWriter = new FileWriter();

    public void addBookToRepository() {
        book = setBookDetails();
        add(book);
    }

    private void add(Book book) {
        bookService.findAllBooks().put(getNewKey(bookService.findAllBooks()), book);
        Map<Long, Book> books = bookService.findAllBooks();
        List<Book> bookList = new ArrayList<>(books.values());
        fileWriter.writeToFile(bookList);
    }

    private long getNewKey(Map<Long, Book> map) {
        return map.entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .get()
                .getKey() + 1;
    }

    public void removeBookFromRepository() {
        long id = getUserInput();
        STDOUT.info("Czy na pewno chcesz usunąć książkę: {}?\n", bookService.findAllBooks().get(id));
        STDOUT.info("Jeżeli tak: wprowadź 1, jeżeli nie: wprowadź 0.\n");
        do {
            int userDecision = getUserInput();
            if (userDecision == 1) {
                remove(id);
                break;
            } else if (userDecision == 0) {
                break;
            }
        } while (true);

    }

    private void remove(long id) {
        bookService.findAllBooks().remove(id);
        Map<Long, Book> books = bookService.findAllBooks();
        List<Book> bookList = new ArrayList<>(books.values());
        fileWriter.writeToFile(bookList);
        STDOUT.info("Książka {} została usunięta z biblioteki.", bookService.findAllBooks().get(id));
        STDOUT.info("Naciśnij ENTER, aby powrócić do poprzedniego menu.");
        scanner.nextLine();
    }


    private Book setBookDetails() {
        cleanTerminal();
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
        STDOUT.info(BOOK_ADDED);
        scanner.nextLine();
        return book;
    }

}


