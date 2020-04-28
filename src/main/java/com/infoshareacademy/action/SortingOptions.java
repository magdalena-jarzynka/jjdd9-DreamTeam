package com.infoshareacademy.action;

import com.infoshareacademy.comparator.SortingByAuthor;
import com.infoshareacademy.comparator.SortingByTitle;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortingOptions {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.\n";
    private static final String SORTING_BY = "sortingBy";
    private static final String SORTING_ORDER = "sortingOrder";
    private final Scanner scanner = new Scanner(System.in);
    private int input;
    private String sortingBy;
    private String sortingOrder;

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

    public String getSortingBy() {
        sortingBy = "";
        STDOUT.info("\n\n Wprowadź 1 aby sortować książki po tytule lub 2 aby sortować je po autorze. \n\n");
        while (true) {
            input = getUserInput();
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
        return sortingBy;
    }

    public String getSortingOrder() {
        sortingOrder = "";
        STDOUT.info("\n\n Wprowadź 1 aby wybrać porządek rosnący lub 2 aby wybrać porządek malejący dla sortowania wyników. \n\n");
        while (true) {
            input = getUserInput();
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
        return sortingOrder;
    }

    public void run() {
        getSortingBy();
        getSortingOrder();
        Configurations.writeToProperties(SORTING_BY, sortingBy);
        Configurations.writeToProperties(SORTING_ORDER, sortingOrder);
    }


    public static List sortList(List list) {
        if (Configurations.getProperties().getProperty(SORTING_BY).equals("TITLE")) {
            if (Configurations.getProperties().getProperty(SORTING_ORDER).equals("ASC")) {
                list.sort(new SortingByTitle());
            } else if (Configurations.getProperties().getProperty(SORTING_ORDER).equals("DESC")) {
                list.sort(Collections.reverseOrder(new SortingByTitle()));
            }
        } else if (Configurations.getProperties().getProperty(SORTING_BY).equals("AUTHOR")) {
            if (Configurations.getProperties().getProperty(SORTING_ORDER).equals("ASC")) {
                list.sort(new SortingByAuthor());
            } else if (Configurations.getProperties().getProperty(SORTING_ORDER).equals("DESC")) {
                list.sort(Collections.reverseOrder(new SortingByAuthor()));
            }
        }
        return list;
    }

}
