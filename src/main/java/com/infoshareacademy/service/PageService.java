package com.infoshareacademy.service;

import com.infoshareacademy.input.UserInputService;

import static com.infoshareacademy.menu.MenuUtils.*;

public class PageService {
    private int positionsPerPage;
    private int currentPageNumber;
    private int numberOfPages;
    UserInputService userInputS = new UserInputService();

    public PageService() {
        currentPageNumber = 1;
    }

    public void choosePagesCount(int listSize) {
        STDOUT.info("\n Ile pozycji wyświetlić na jednej stronie? \n");
        positionsPerPage = userInputS.getUserInput();
        if (positionsPerPage > 0) {
            numberOfPages = (int) Math.ceil((double) listSize / positionsPerPage);
        }
    }

    public long findFirstPosition() {
        return ((long) currentPageNumber - 1) * positionsPerPage;
    }

    public void increasePagesCount() {
        currentPageNumber++;
    }

    public void decreasePagesCount() {
        currentPageNumber--;
    }

    public boolean isLastPage() {
        return currentPageNumber == numberOfPages;
    }

    public boolean isFirstPage() {
        return currentPageNumber == 1;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getPositionsPerPage() {
        return positionsPerPage;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }
}
