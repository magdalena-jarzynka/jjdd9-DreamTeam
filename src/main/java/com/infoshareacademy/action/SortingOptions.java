package com.infoshareacademy.action;

import com.infoshareacademy.input.UserInputService;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.WRONG_NUMBER;

public class SortingOptions {
    private static final String SORTING_BY = "sortingBy";
    private static final String SORTING_ORDER = "sortingOrder";
    private final Scanner scanner = new Scanner(System.in);
    private int input;
    private String sortingBy;
    private String sortingOrder;
    private UserInputService userInputService = new UserInputService();



    public void getSortingBy() {
        sortingBy = "";
        STDOUT.info("\n\n Wprowadź 1 aby sortować książki po tytule lub 2 aby sortować je po autorze. \n\n");
        while (true) {
            input = userInputService.getUserInput();
            if (input == 1) {
                sortingBy = "TITLE";
            } else if (input == 2) {
                sortingBy = "AUTHOR";
            } else {
                STDOUT.info(WRONG_NUMBER);
            }
            if (input == 1 || input == 2) {
                break;
            }
        }
    }

    public void getSortingOrder() {
        sortingOrder = "";
        STDOUT.info("\n\n Wprowadź 1 aby wybrać porządek rosnący lub 2 aby wybrać porządek malejący dla sortowania wyników. \n\n");
        while (true) {
            input = userInputService.getUserInput();
            if (input == 1) {
                sortingOrder = "ASC";
            } else if (input == 2) {
                sortingOrder = "DESC";
            } else {
                STDOUT.info(WRONG_NUMBER);
            }
            if (input == 1 || input == 2) {
                break;
            }
        }
    }

    public void run() {
        getSortingBy();
        getSortingOrder();
        Configurations.writeToProperties(SORTING_BY, sortingBy);
        Configurations.writeToProperties(SORTING_ORDER, sortingOrder);
    }

}
