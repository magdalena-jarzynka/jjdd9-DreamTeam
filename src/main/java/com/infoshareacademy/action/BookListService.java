package com.infoshareacademy.action;

import com.infoshareacademy.ConsoleColors;
import com.infoshareacademy.menu.Menu;
import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookListService {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.\n\n";
    private int input;
    private int positionsPerPage;
    private int currentPageNumber;
    private int numberOfPages;

    public BookListService() {
        this.currentPageNumber = 1;
        this.numberOfPages = 2;
        this.input = 0;
    }

    public int getNumberOfPages() {
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
            input = getNumberOfPages();
        }
        if (input < 0) {
            STDOUT.info(WRONG_NUMBER);
            input = getNumberOfPages();
        }
        return input;
    }

    public int getUserInput() {
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
            input = getUserInput();
        }
        return input;
    }


    public void run() {

        STDOUT.info("\n Ile pozycji wyświetlić na jednej stronie? \n");
        positionsPerPage = getNumberOfPages();
        do {
            Menu menu = new Menu();
            menu.cleanTerminal();
            getBooksList();
            input = 0;
            input = getUserInput();
            switch (input) {
                case 1:
                    if (currentPageNumber < numberOfPages) {
                        currentPageNumber = currentPageNumber + 1;
                    }
                    break;
                case 2:
                    if (currentPageNumber > 1) {
                        currentPageNumber = currentPageNumber - 1;
                    }
                    break;
                case 0:
                    return;
                default:
                    input = getUserInput();
            }
        } while (true);
    }

    public void getBooksList() {
        Menu menu = new Menu();
        menu.cleanTerminal();
        Map<Long, Book> books = BookRepository.getInstance().getBooks();
        if (positionsPerPage > 0) {
            numberOfPages = (int) Math.ceil((double) books.size() / positionsPerPage);
        }
        int firstPositionOnPage = (currentPageNumber - 1) * positionsPerPage;
        int lastPositionOnPage = firstPositionOnPage + positionsPerPage;

        for (int i = 0; i < positionsPerPage; i++) {
            if (lastPositionOnPage > books.size()) {
                lastPositionOnPage = lastPositionOnPage - 1;
            }
        }

        List<Long> keys = new ArrayList<>();
        for (int i = 0; i <positionsPerPage ; i++) {
            keys.add((long) (i + 1));
        }

        Map<Long, Book> booksPerPage = keys.stream()
                .filter(books :: containsKey)
                .collect(Collectors.toMap(Function.identity(), books::get));

        AtomicReference<Long> positionNumber = new AtomicReference<>(1L);

        booksPerPage.entrySet().forEach(b -> {
            STDOUT.info("{}{}.Tytuł: {}{}{}{} \n", ConsoleColors.BLACK_BOLD.getColorType(), positionNumber.get(),
                    ConsoleColors.RESET.getColorType(), ConsoleColors.RED.getColorType(), b.getValue().getTitle(), ConsoleColors.RESET.getColorType());
            STDOUT.info(" {} Autor: {}{}{}{} \n\n", ConsoleColors.BLACK_BOLD.getColorType(),
                    ConsoleColors.RESET.getColorType(), ConsoleColors.BLUE.getColorType(), b.getValue().getAuthors().get(0).getName(), ConsoleColors.RESET.getColorType());
            positionNumber.getAndSet(positionNumber.get() + 1);
        });

        if (currentPageNumber == numberOfPages) {
            STDOUT.info("\n To ostatnia strona. " +
                    "Wybierz 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do głównego menu. \n\n");
        } else {
            STDOUT.info("\n Wybierz 1 aby zobaczyć następną stronę, 2 aby zobaczyć poprzednią lub 0 aby wyjść do " +
                    "poprzedniego menu. \n\n");
        }
        STDOUT.info("\n{}Strona {} z {}.{}\n", ConsoleColors.BLACK_UNDERLINED.getColorType(), currentPageNumber,
                numberOfPages, ConsoleColors.RESET.getColorType());
    }
}
