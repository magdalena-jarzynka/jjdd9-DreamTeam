package com.infoshareacademy.service.management;

import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.infoshareacademy.menu.MenuUtils.*;

public class BooksOperations {
    private static final String ENTER_TITLE = "Proszę wpisać tytuł: ";
    private static final String ENTER_AUTHOR = "Proszę wpisać autora: ";
    private static final String ENTER_TRANSLATORS = "Proszę wpisać tłumacza/y: ";
    private static final String ENTER_EPOCH = "Proszę wpisać epokę literacką: ";
    private static final String ENTER_GENRE = "Proszę wpisać gatunek książki: ";
    private static final String ENTER_KIND = "Proszę wpisać gatunek literacki: ";
    private static final String ENTER_ISBN = "Proszę wpisać numer ISBN książki: ";
    private static final String ENTER_FRAGMENT = "Proszę wpisać fragment książki: ";
    private static final String ENTER_MEDIA = "Proszę wpisać nazwę audiobooka jeżeli posiada: ";
    private static final String BOOK_ADDED = "\n\nZakończono dodawanie pozycji, wciśnij ENTER aby wrócić " +
            "do poprzedniego widoku.\n\n";
    private final Scanner scanner = new Scanner(System.in);
    private Book book = new Book();
    private BookService bookService = new BookService();
    private FileWriter fileWriter = new FileWriter();
    private BookDefinitionService bookDefinitionService = new BookDefinitionService();
    private UserInputService userInputService = new UserInputService();

    public void addBookToRepository() {
        book = setBookDetails();
        add(book);
    }

    private Book setBookDetails() {
        cleanTerminal();
        STDOUT.info(ENTER_TITLE);
        bookDefinitionService.defineTitle(book, scanner.nextLine());
        STDOUT.info(ENTER_AUTHOR);
        bookDefinitionService.defineAuthor(book, scanner.nextLine());
        STDOUT.info(ENTER_TRANSLATORS);
        bookDefinitionService.defineTranslator(book, scanner.nextLine());
        STDOUT.info(ENTER_EPOCH);
        bookDefinitionService.defineEpoch(book, scanner.nextLine());
        STDOUT.info(ENTER_GENRE);
        bookDefinitionService.defineGenre(book, scanner.nextLine());
        STDOUT.info(ENTER_KIND);
        bookDefinitionService.defineKind(book, scanner.nextLine());
        STDOUT.info(ENTER_ISBN);
        bookDefinitionService.defineIsbn(book, scanner.nextLine());
        STDOUT.info(ENTER_FRAGMENT);
        bookDefinitionService.defineFragment(book, scanner.nextLine());
        STDOUT.info(ENTER_MEDIA);
        bookDefinitionService.defineMedia(book, scanner.nextLine());
        STDOUT.info(BOOK_ADDED);
        scanner.nextLine();
        return book;
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
        STDOUT.info("Wprowadź ID książki, którą chcesz usunąć z biblioteki\n");
        long id;
        do {
            id = userInputService.getUserInput();
            if (bookService.findAllBooks().containsKey(id)) {
                break;
            } else {
                STDOUT.info("\nPozycja o podanym ID nie istnieje\n");
            }
        } while (true);
        String bookToBeDeleted = bookService.findAllBooks().get(id).getTitle();
        STDOUT.info("Czy na pewno chcesz usunąć książkę: {}?\n", bookToBeDeleted);
        STDOUT.info("Jeżeli tak: wprowadź 1, jeżeli nie: wprowadź 0.\n");
        do {
            int userDecision = userInputService.getUserInput();
            if (userDecision == 1) {
                remove(id);
                STDOUT.info("Książka {} została usunięta z biblioteki.\n", bookToBeDeleted);
                STDOUT.info("Naciśnij ENTER, aby powrócić do poprzedniego menu.\n");
                scanner.nextLine();
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
    }

    public void modifyBook() {
        STDOUT.info("Wprowadź ID książki, którą chcesz zmodyfikować\n\n");
        long id;
        do {
            id = userInputService.getUserInput();
            if (bookService.findAllBooks().containsKey(id)) {
                break;
            } else {
                STDOUT.info("\nPozycja o podanym ID nie istnieje\n");
            }
        } while (true);
        STDOUT.info("Poniżej znajdują się informacje o wybranej książce.\n\n");
        STDOUT.info(bookService.getBookDetails(id));
        getBookModifications(id);
        STDOUT.info("Zakończono edycję książki.\n");
        saveModifiedBook();
        STDOUT.info("Wciśnij enter, aby powrócić do poprzedniego widoku.\n");
        scanner.nextLine();
    }

    private void getBookModifications(long id) {
        book = bookService.findAllBooks().get(id);
        do {
            STDOUT.info("\nWybierz numer pozycji, którą chcesz zmodyfikować.\n");
            STDOUT.info("Twoje zmiany będą widoczne bo zakończeniu edycji.\n");
            STDOUT.info("Wybierz 0, aby zakończyć edycję książki.\n");
            int userChoice = userInputService.getUserInput();
            switch (userChoice) {
                case 1:
                    STDOUT.info(ENTER_TITLE);
                    bookDefinitionService.defineTitle(book, scanner.nextLine());
                    break;
                case 2:
                    STDOUT.info(ENTER_AUTHOR);
                    bookDefinitionService.defineAuthor(book, scanner.nextLine());
                    break;
                case 3:
                    STDOUT.info(ENTER_TRANSLATORS);
                    bookDefinitionService.defineTranslator(book, scanner.nextLine());
                    break;
                case 4:
                    STDOUT.info(ENTER_EPOCH);
                    bookDefinitionService.defineEpoch(book, scanner.nextLine());
                    break;
                case 5:
                    STDOUT.info(ENTER_GENRE);
                    bookDefinitionService.defineGenre(book, scanner.nextLine());
                    break;
                case 6:
                    STDOUT.info(ENTER_KIND);
                    bookDefinitionService.defineKind(book, scanner.nextLine());
                    break;
                case 7:
                    STDOUT.info(ENTER_ISBN);
                    bookDefinitionService.defineIsbn(book, scanner.nextLine());
                    break;
                case 8:
                    STDOUT.info(ENTER_FRAGMENT);
                    bookDefinitionService.defineTitle(book, scanner.nextLine());
                    break;
                case 9:
                    STDOUT.info(ENTER_MEDIA);
                    bookDefinitionService.defineMedia(book, scanner.nextLine());
                    break;
                case 0:
                    return;
                default:
                    STDOUT.info(WRONG_NUMBER);
            }
        } while (true);
    }

    public void saveModifiedBook() {
        Map<Long, Book> books = bookService.findAllBooks();
        List<Book> bookList = new ArrayList<>(books.values());
        fileWriter.writeToFile(bookList);
    }

}


