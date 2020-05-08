package com.infoshareacademy.service;

import com.infoshareacademy.input.UserInputService;

import static com.infoshareacademy.menu.MenuUtils.*;

public class PageService {
    private int positionsPerPage;
    private int currentPageNumber;
    private int currentChapterNumber;
    private int numberOfPages;
    private int numberOfChapters;
    private int[] pagesPerChapter;
    public static final String LAST_PAGE = "\nTo ostatnia strona. " +
            "Wybierz 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do poprzedniego menu. \n";
    public static final String NEXT_PAGE = "\nWybierz 1 aby zobaczyć następną stronę, 2 aby zobaczyć poprzednią lub 0 aby wyjść do " +
            "poprzedniego menu. \n";
    UserInputService userInputS = new UserInputService();

    public PageService() {
        currentPageNumber = 1;
        currentChapterNumber = 1;
    }

    public void choosePagesCount(int listSize) {
        STDOUT.info("\n Ile pozycji wyświetlić na jednej stronie? \n");
        positionsPerPage = userInputS.getUserInput();
        if (positionsPerPage > 0) {
            numberOfPages = (int) Math.ceil((double) listSize / positionsPerPage);
        }
        cleanTerminal();
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

    public void initChapters(int numberOfChapters) {
        this.numberOfChapters = numberOfChapters;

    }

    public void increaseChaptersCount() {
        currentChapterNumber++;
        currentPageNumber = 1;
    }

    public void decreaseChaptersCount() {
        currentChapterNumber--;
        currentPageNumber = 1;
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

    public int getCurrentChapterNumber() {
        return currentChapterNumber;
    }

    public void setNumberOfChapters(int numberOfChapters) {
        this.numberOfChapters = numberOfChapters;
    }
}
