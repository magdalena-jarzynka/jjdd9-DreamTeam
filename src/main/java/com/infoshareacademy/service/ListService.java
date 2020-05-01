package com.infoshareacademy.service;

import com.infoshareacademy.object.Book;
import com.infoshareacademy.repository.BookRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Scanner;

public class ListService {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.\n\n";
    private int input;
    private int positionsPerPage;
    private int currentPageNumber;
    private int numberOfPages;
    private long positionNumber;
    private long firstPositionOnPage;
    private long lastPositionOnPage;



    public int getPageSize() {
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
            input = getPageSize();
        }
        if (input < 0) {
            STDOUT.info(WRONG_NUMBER);
            input = getPageSize();
        }
        return input;
    }

    private int getNumberOfPages() {
        Map<Long, Book> books = BookRepository.getInstance().getBooks();
        if (positionsPerPage > 0) {
            numberOfPages = (int) Math.ceil((double) books.size() / positionsPerPage);
        }
        return numberOfPages;
    }

    private long findFirstPosition() {
        return ((long) currentPageNumber - 1) * positionsPerPage;
    }

    private long findLastPosition(){
        Map<Long, Book> books = BookRepository.getInstance().getBooks();
        lastPositionOnPage =  firstPositionOnPage + positionsPerPage;
        for (int i = 0; i < positionsPerPage; i++) {
            if (lastPositionOnPage > books.size()) {
                lastPositionOnPage = lastPositionOnPage - 1;
            }
        }
        return lastPositionOnPage;
    }

    private long findPositionNumber(){
        return firstPositionOnPage + 1;
    }
}
