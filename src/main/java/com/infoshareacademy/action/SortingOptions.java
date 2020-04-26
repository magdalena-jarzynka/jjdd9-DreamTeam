package com.infoshareacademy.action;

import com.infoshareacademy.comparator.compareByAuthor;
import com.infoshareacademy.comparator.compareByTitle;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortingOptions {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String WRONG_NUMBER = "Proszę wpisać odpowiednią cyfrę.\n";
    private final Scanner scanner = new Scanner(System.in);
    private int input;

    public SortingOptions() {
    }

    public int getUserInput() {
        String lineInput = scanner.nextLine();
        if (NumberUtils.isCreatable(lineInput)) {
            input = Integer.parseInt(lineInput);
        } else {
            STDOUT.info(WRONG_NUMBER);
        }
        return input;
    }

    public void run() {
        String sortingBy;
        String sortingOrder;
        STDOUT.info("\n\n Wprowadź 1 aby sortować książki po tytule lub 2 aby sortować je po autorze. \n\n");
        while(true) {
            input = getUserInput();
            if (input == 1) {
                sortingBy = "TITLE";
                break;
            } else if (input == 2) {
                sortingBy = "AUTHOR";
                break;
            } else {
                STDOUT.info(WRONG_NUMBER);
            }
        }

        STDOUT.info("\n\n Wprowadź 1 aby wybrać porządek rosnący lub 2 aby wybrać porządek malejący dla sortowania wyników. \n\n");
        while(true) {
            input = getUserInput();
            if (input == 1) {
                sortingOrder = "ASC";
                break;
            } else if (input == 2) {
                sortingOrder = "DESC";
                break;
            } else {
                STDOUT.info(WRONG_NUMBER);
            }
        }
        Configurations.writeToProperties("sortingBy", sortingBy);
        Configurations.writeToProperties("sortingOrder", sortingOrder);
    }

    public static List sortList(List list) {
        if (Configurations.getProperties().getProperty("sortingBy").equals("TITLE")) {
            if (Configurations.getProperties().getProperty("sortingOrder").equals("ASC")) {
                list.sort(new compareByTitle());
            } else if (Configurations.getProperties().getProperty("sortingOrder").equals("DESC")) {
                list.sort(Collections.reverseOrder(new compareByTitle()));
            }
        } else if (Configurations.getProperties().getProperty("sortingBy").equals("AUTHOR")) {
            if (Configurations.getProperties().getProperty("sortingOrder").equals("ASC")) {
                list.sort(new compareByAuthor());
            } else if (Configurations.getProperties().getProperty("sortingOrder").equals("DESC")) {
                list.sort(Collections.reverseOrder(new compareByAuthor()));
            }
        }
        return list;
    }

}
