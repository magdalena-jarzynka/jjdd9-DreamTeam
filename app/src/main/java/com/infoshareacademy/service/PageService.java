package com.infoshareacademy.service;

import com.infoshareacademy.input.UserInputService;

import java.util.ArrayList;
import java.util.List;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.cleanTerminal;

public class PageService {
    private int positionsPerPage;
    private int currentPageNumber;
    private int currentChapterNumber;
    private final List<Integer> pagesPerChapter = new ArrayList<>();
    public static final String LAST_PAGE = "\nTo ostatnia strona. " +
            "Wybierz 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do poprzedniego menu. \n";
    public static final String NEXT_PAGE = "\nWybierz 1 aby zobaczyć następną stronę, 2 aby zobaczyć poprzednią lub 0 aby wyjść do " +
            "poprzedniego menu. \n";
    public static final String LAST_PAGE_NEXT_CHAPTER = "\nTo ostatnia strona rozdziału. " +
            "Wybierz 1 aby zobaczyć następny rozdział, 2 aby zobaczyć poprzednią stronę lub 0 aby wyjść do poprzedniego menu. \n";
    UserInputService userInputS = new UserInputService();

    public PageService() {
        currentPageNumber = 1;
        currentChapterNumber = 1;
    }

    public void choosePagesCount() {
        STDOUT.info("\n Ile pozycji wyświetlić na jednej stronie? \n");
        while(true) {
            positionsPerPage = userInputS.getUserInput();
            if(positionsPerPage > 0) {
                break;
            } else {
                STDOUT.info("Liczba pozycji na stronie musi być większa niż 0\n");
            }
        }
        cleanTerminal();
    }

    public long findFirstPosition() {
        return ((long) currentPageNumber - 1) * positionsPerPage;
    }

    public void increasePagesCount() {
        if (isLastPage() && !isLastChapter()) {
            increaseChaptersCount();
        } else if (!isLastPage()) {
            currentPageNumber++;
        }
    }

    public void decreasePagesCount() {
        if (isFirstPage() && !isFirstChapter()) {
            decreaseChaptersCount();
        } else if (!isFirstPage()) {
            currentPageNumber--;
        }
    }

    public void addChapter(int numberOfPositions) {
        if (positionsPerPage > 0) {
            int numberOfPages = (int) Math.ceil((double) numberOfPositions / positionsPerPage);
            if (numberOfPages != 0) {
                pagesPerChapter.add(numberOfPages);
            } else {
                pagesPerChapter.add(1);
            }
        }
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
        return currentPageNumber == pagesPerChapter.get(currentChapterNumber - 1);
    }

    public boolean isFirstPage() {
        return currentPageNumber == 1;
    }

    public boolean isLastChapter() {
        return currentChapterNumber == pagesPerChapter.size();
    }

    public boolean isFirstChapter() {
        return currentChapterNumber == 1;
    }

    public int getNumberOfPages() {
        return pagesPerChapter.stream()
                .reduce((sum, i) -> sum += i)
                .orElse(0);
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

    public void setCurrentChapterNumber(int currentChapterNumber) {
        this.currentChapterNumber = currentChapterNumber;
    }
}
